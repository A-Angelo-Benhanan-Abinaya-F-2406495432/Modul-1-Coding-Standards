package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderService orderService;

    private Map<String, Order> paymentOrderMap = new HashMap<>();

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(UUID.randomUUID().toString(), method, paymentData);
        String status = resolveStatus(method, paymentData);
        payment.setStatus(status);

        Payment savedPayment = paymentRepository.save(payment);
        paymentOrderMap.put(savedPayment.getId(), order);
        return savedPayment;
    }

    private boolean validateVoucherCode(String code) {
        if (code == null || code.length() != 16) {
            return false;
        }

        if (!code.startsWith("ESHOP")) {
            return false;
        }

        return code.chars().filter(Character::isDigit).count() == 8;
    }

    private String resolveStatus(String method, Map<String, String> paymentData) {
        if ("VOUCHER".equals(method)) {
            if (validateVoucherCode(paymentData.get("voucherCode"))) {
                return PaymentStatus.SUCCESS.getValue();
            } else {
                return PaymentStatus.REJECTED.getValue();
            }
        }

        if ("BANK_TRANSFER".equals(method)) {
            String bankName = paymentData.get("bankName");
            String referenceCode = paymentData.get("referenceCode");
            if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
                return PaymentStatus.REJECTED.getValue();
            } else {
                return PaymentStatus.SUCCESS.getValue();
            }
        }

        return PaymentStatus.WAITING.getValue();
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);

        Order relatedOrder = paymentOrderMap.get(payment.getId());
        if (relatedOrder != null) {
            if (PaymentStatus.SUCCESS.getValue().equals(status)) {
                orderService.updateStatus(relatedOrder.getId(), OrderStatus.SUCCESS.getValue());
            } else if (PaymentStatus.REJECTED.getValue().equals(status)) {
                orderService.updateStatus(relatedOrder.getId(), OrderStatus.FAILED.getValue());
            }
        }

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}

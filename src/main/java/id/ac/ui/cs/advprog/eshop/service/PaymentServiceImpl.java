package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@NoArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderService orderService;

    private final Map<String, Order> paymentOrderMap = new HashMap<>();

    @Override
    public Payment addPayment(final Order order, final String method, final Map<String, String> paymentData) {
        final Payment payment = new Payment(UUID.randomUUID().toString(), method, paymentData);
        final String status = resolveStatus(method, paymentData);
        payment.setStatus(status);

        final Payment savedPayment = paymentRepository.save(payment);
        paymentOrderMap.put(savedPayment.getPaymentId(), order);
        return savedPayment;
    }

    private boolean validateVoucherCode(final String code) {
        boolean codeIsValid;
        if (code != null && code.length() == 16 && code.startsWith("ESHOP")) {
            codeIsValid = code.chars().filter(Character::isDigit).count() == 8;
        } else {
            codeIsValid = false;
        }

        return codeIsValid;
    }

    private String resolveStatus(final String method, final Map<String, String> paymentData) {
        String paymentStatus = PaymentStatus.WAITING.getValue();
        if (PaymentMethod.VOUCHER.getValue().equals(method)) {
            if (validateVoucherCode(paymentData.get("voucherCode"))) {
                paymentStatus = PaymentStatus.SUCCESS.getValue();
            } else {
                paymentStatus = PaymentStatus.REJECTED.getValue();
            }
        }

        if (PaymentMethod.BANK_TRANSFER.getValue().equals(method)) {
            final String bankName = paymentData.get("bankName");
            final String referenceCode = paymentData.get("referenceCode");
            if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
                paymentStatus = PaymentStatus.REJECTED.getValue();
            } else {
                paymentStatus = PaymentStatus.SUCCESS.getValue();
            }
        }

        return paymentStatus;
    }

    @Override
    public void setStatus(final Payment payment, final String status) {
        payment.setStatus(status);

        final Order relatedOrder = paymentOrderMap.get(payment.getPaymentId());
        if (relatedOrder != null) {
            if (PaymentStatus.SUCCESS.getValue().equals(status)) {
                orderService.updateStatus(relatedOrder.getOrderId(), OrderStatus.SUCCESS.getValue());
            } else if (PaymentStatus.REJECTED.getValue().equals(status)) {
                orderService.updateStatus(relatedOrder.getOrderId(), OrderStatus.FAILED.getValue());
            }
        }

        paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(final String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}

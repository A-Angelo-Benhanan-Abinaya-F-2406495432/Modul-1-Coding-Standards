package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        payments.add(new Payment("pay-001", "VOUCHER", paymentData1));

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "BCA");
        paymentData2.put("referenceCode", "REF123456");
        payments.add(new Payment("pay-002", "BANK_TRANSFER", paymentData2));
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.getFirst();
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payment.getPaymentId());
        assertEquals(payment.getPaymentId(), result.getPaymentId());
        assertEquals(payment.getPaymentId(), findResult.getPaymentId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.getFirst();
        paymentRepository.save(payment);

        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payment.getPaymentId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfFound() {
        for (Payment p : payments) paymentRepository.save(p);

        Payment findResult = paymentRepository.findById(payments.get(1).getPaymentId());
        assertEquals(payments.get(1).getPaymentId(), findResult.getPaymentId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
    }

    @Test
    void testFindByIdIfNotFound() {
        for (Payment p : payments) paymentRepository.save(p);

        Payment findResult = paymentRepository.findById("zczc");
        assertNull(findResult);
    }

    @Test
    void testFindAll() {
        for (Payment p : payments) paymentRepository.save(p);

        List<Payment> result = paymentRepository.findAll();
        assertEquals(2, result.size());
    }
}

package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class PaymentTest {
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
    }

    @Test
    void testCreatePaymentVoucherCode() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("pay-001", "VOUCHER", paymentData);

        assertEquals("pay-001", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertSame(paymentData, payment.getPaymentData());
        assertEquals("WAITING", payment.getStatus());
    }

    @Test
    void testCreatePaymentBankTransfer() {
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "REF123456");
        Payment payment = new Payment("pay-002", "BANK_TRANSFER", paymentData);

        assertEquals("pay-002", payment.getId());
        assertEquals("BANK_TRANSFER", payment.getMethod());
        assertSame(paymentData, payment.getPaymentData());
        assertEquals("WAITING", payment.getStatus());
    }

    @Test
    void testSetStatusSuccess() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("pay-001", "VOUCHER", paymentData);

        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusRejected() {
        paymentData.put("voucherCode", "INVALID");
        Payment payment = new Payment("pay-002", "VOUCHER", paymentData);

        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }
}
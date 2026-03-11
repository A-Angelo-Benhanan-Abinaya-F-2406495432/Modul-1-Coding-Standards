package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Repository
public class PaymentRepository {
    private final List<Payment> payments = new ArrayList<>();

    public Payment save(final Payment payment) {
        boolean found = false;
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getPaymentId().equals(payment.getPaymentId())) {
                payments.set(i, payment);
                found = true;
                break;
            }
        }

        if (!found) {
            payments.add(payment);
        }

        return payment;
    }

    public Payment findById(final String paymentId) {
        Payment paymentToFind = null;
        for (final Payment payment : payments) {
            if (payment.getPaymentId().equals(paymentId)) {
                paymentToFind = payment;
            }
        }
        return paymentToFind;
    }

    public List<Payment> findAll() {
        return new ArrayList<>(payments);
    }
}

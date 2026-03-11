package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class Payment {
    private String paymentId;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(final String paymentId, final String method, final Map<String, String> paymentData) {
        this.paymentId = paymentId;
        this.method = method;
        this.status = PaymentStatus.WAITING.getValue();
        this.paymentData = paymentData;
    }

    public Payment(final String paymentId, final String method, final Map<String, String> paymentData, final String status) {
        this(paymentId, method, paymentData);

        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setStatus(final String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}

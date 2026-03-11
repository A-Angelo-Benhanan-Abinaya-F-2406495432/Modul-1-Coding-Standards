package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    WAITING("WAITING"),
    SUCCESS("SUCCESS"),
    REJECTED("REJECTED");

    private final String value;

    PaymentStatus(final String value) {
        this.value = value;
    }

    public static boolean contains(final String param) {
        boolean containsBool = false;
        for (final PaymentStatus status : values()) {
            if (status.getValue().equals(param)) {
                containsBool = true;
                break;
            }
        }
        return containsBool;
    }
}

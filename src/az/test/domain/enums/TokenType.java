package az.test.domain.enums;

import com.sun.org.apache.regexp.internal.RE;

public enum TokenType {
    ACTIVATION, RESEND, EXPIRE;

    public static TokenType fromValue(int value) {
        if (value == 1) {
            return TokenType.ACTIVATION;
        } else if (value == 2) {
            return TokenType.RESEND;
        } else if (value == 3) {
            return TokenType.EXPIRE;
        } else {
            return null;
        }
    }

    public static int toValue(TokenType value) {
        if (value.equals(ACTIVATION)) {
            return 1;
        } else if (value.equals(RESEND)) {
            return 2;
        } else if (value.equals(EXPIRE)) {
            return 3;
        } else {
            return 0;
        }

    }
}

package az.test.domain.enums;

public enum Status {
    ACTIVE, DELETED;

    public static Status fromValue(int value) {
        if (value == 1) {
            return Status.ACTIVE;
        } else if (value == 0) {
            return Status.DELETED;
        } else {
            return null;
        }
    }

    public static int toValue(Status value) {
        if (value.equals(ACTIVE)) {
            return 1;
        } else {
            return 0;
        }

    }

}

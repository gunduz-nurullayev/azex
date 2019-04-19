package az.test.domain.enums;

public enum UserStatus {
    PENDING, ACTIVE;
    public static UserStatus fromValue(int value){
        if (value==0){
            return UserStatus.PENDING;
        }
        else if(value==1)  {
            return  UserStatus.ACTIVE;
        }
        else {
            return null;
        }
    }
    public static int toValue(UserStatus value) {
        if (value.equals(ACTIVE)) {
            return 1;
        } else {
            return 0;
        }

    }
}

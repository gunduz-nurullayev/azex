package az.test.domain;

import az.test.domain.enums.Status;
import az.test.domain.enums.UserStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Customer extends User implements Serializable {

    private static final long serialVersionUID = -9133556689817678296L;
     private String customerCode;
     private long channel;
     private String referalCode;
     private long city;
     private long district;
     private String address;



    public Customer(long id, Status status, String name, String surname, String email, String password, String idCardPrefix, String idCardNumber, String pinCode, int gender, String phone1, String phone2, LocalDate birthDate, LocalDateTime registrationDate, String token, LocalDateTime tokenExpireDate, LocalDateTime activationDate, int userType, UserStatus userStatus, String customerCode, long channel, String referalCode, long city, long district, String address) {
        super(id, status, name, surname, email, password, idCardPrefix, idCardNumber, pinCode, gender, phone1, phone2, birthDate, registrationDate, token, tokenExpireDate, activationDate, userType, userStatus);
        this.customerCode = customerCode;
        this.channel = channel;
        this.referalCode = referalCode;
        this.city = city;
        this.district = district;
        this.address = address;
    }

    public Customer(String customerCode, long channel, String referalCode, long city, long district, String address) {
        this.customerCode = customerCode;
        this.channel = channel;
        this.referalCode = referalCode;
        this.city = city;
        this.district = district;
        this.address = address;
    }

    public Customer() {
        /*this.customerCode = "";
        this.channel = 0;
        this.referalCode = "";
        this.city = 0;
        this.district = 0;
        this.address = "";*/
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public long getChannel() {
        return channel;
    }

    public void setChannel(long channel) {
        this.channel = channel;
    }

    public String getReferalCode() {
        return referalCode;
    }

    public void setReferalCode(String referalCode) {
        this.referalCode = referalCode;
    }

    public long getCity() {
        return city;
    }

    public void setCity(long city) {
        this.city = city;
    }

    public long getDistrict() {
        return district;
    }

    public void setDistrict(long district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return channel == customer.channel &&
                city == customer.city &&
                district == customer.district &&
                Objects.equals(customerCode, customer.customerCode) &&
                Objects.equals(referalCode, customer.referalCode) &&
                Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerCode, channel, referalCode, city, district, address);
    }
}

package az.test.domain;

import az.test.domain.enums.Status;
import az.test.domain.enums.UserStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 6799115754477631083L;
    protected String name;
    protected String surname;
    protected String email;
    protected String password;
    protected String idCardPrefix;
    protected String idCardNumber;
    protected String pinCode;
    protected int gender;
    protected String phone1;
    protected String phone2;
    protected LocalDate birthDate;
    protected LocalDateTime registrationDate;
    protected String token;
    protected LocalDateTime tokenExpireDate;
    protected LocalDateTime activationDate;
    protected long userType;
    protected UserStatus userStatus;
    protected List<Role> roleList;


    public User(String name, String surname, String email, String password, long userType, UserStatus userStatus, LocalDateTime registrationDate, String token, LocalDateTime tokenExpireDate) {
        super();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.userStatus = userStatus;
        this.registrationDate=registrationDate;
        this.token=token;
        this.tokenExpireDate=tokenExpireDate;
    }

    public User(long id, Status status, String name, String surname, String email, String password, String idCardPrefix, String idCardNumber, String pinCode, int gender, String phone1, String phone2, LocalDate birthDate, LocalDateTime registrationDate, String token, LocalDateTime tokenExpireDate, LocalDateTime activationDate, int userType, UserStatus userStatus) {
        super(id, status);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.idCardPrefix = idCardPrefix;
        this.idCardNumber = idCardNumber;
        this.pinCode = pinCode;
        this.gender = gender;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.token = token;
        this.tokenExpireDate = tokenExpireDate;
        this.activationDate = activationDate;
        this.userType = userType;
        this.userStatus = userStatus;
    }

    public User(String idCardPrefix, String idCardNumber, String pinCode, int gender, String phone1, String phone2, LocalDate birthDate, LocalDateTime activationDate) {
        this.idCardPrefix = idCardPrefix;
        this.idCardNumber = idCardNumber;
        this.pinCode = pinCode;
        this.gender = gender;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.birthDate = birthDate;
        this.activationDate = activationDate;
    }

    public User() {
        this.roleList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCardPrefix() {
        return idCardPrefix;
    }

    public void setIdCardPrefix(String idCardPrefix) {
        this.idCardPrefix = idCardPrefix;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getTokenExpireDate() {
        return tokenExpireDate;
    }

    public void setTokenExpireDate(LocalDateTime tokenExpireDate) {
        this.tokenExpireDate = tokenExpireDate;
    }

    public LocalDateTime getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDateTime activationDate) {
        this.activationDate = activationDate;
    }

    public long getUserType() {
        return userType;
    }

    public void setUserType(long userType) {
        this.userType = userType;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return gender == user.gender &&
                userType == user.userType &&
                userStatus == user.userStatus &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(idCardPrefix, user.idCardPrefix) &&
                Objects.equals(idCardNumber, user.idCardNumber) &&
                Objects.equals(pinCode, user.pinCode) &&
                Objects.equals(phone1, user.phone1) &&
                Objects.equals(phone2, user.phone2) &&
                Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(registrationDate, user.registrationDate) &&
                Objects.equals(token, user.token) &&
                Objects.equals(tokenExpireDate, user.tokenExpireDate) &&
                Objects.equals(activationDate, user.activationDate) &&
                Objects.equals(roleList, user.roleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, email, password, idCardPrefix, idCardNumber, pinCode, gender, phone1, phone2, birthDate, registrationDate, token, tokenExpireDate, activationDate, userType, userStatus, roleList);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", idCardPrefix='" + idCardPrefix + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", gender=" + gender +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", birthDate=" + birthDate +
                ", registrationDate=" + registrationDate +
                ", token='" + token + '\'' +
                ", tokenExpireDate=" + tokenExpireDate +
                ", activationDate=" + activationDate +
                ", userType=" + userType +
                ", userStatus=" + userStatus +
                ", roleList=" + roleList +
                ", status=" + status +
                '}';
    }
}

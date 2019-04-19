package az.test.domain;

import az.test.domain.enums.Status;

import java.io.Serializable;
import java.math.BigDecimal;

public class Employee extends BaseDomain implements Serializable {

            private static final long serialVersionUID = 819363201361705022L;
    private String name;
    private long age;
    private String adress;
    private BigDecimal salary;

    public Employee(long id, String name, long age, String adress, BigDecimal salary) {
        super(id, Status.ACTIVE);
        this.name = name;
        this.age = age;
        this.adress = adress;
        this.salary = salary;
    }

    public Employee() {
        this.name = "";
        this.age = 0;
        this.adress = "";
        this.salary = BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name=" + name +
                ", age=" + age +
                ", adress='" + adress + '\'' +
                ", salary=" + salary +
                ", status=" + status +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}

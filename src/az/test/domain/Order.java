package az.test.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order extends  BaseDomain implements Serializable {
    private static final long serialVersionUID = -6409828871744752162L;
    private Customer customer;
    private LocalDateTime orderDate;
    private BigDecimal balance;
    public Order() {
    }
}

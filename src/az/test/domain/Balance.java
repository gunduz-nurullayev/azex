package az.test.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Balance extends BaseDomain implements Serializable {

    private static final long serialVersionUID = 4360015206206870707L;

    protected BigDecimal amount;
    protected long currencyId;
    protected long customerId;
    protected LocalDateTime lastUpdate;

    public Balance() {
        this.amount = BigDecimal.ZERO;
        this.currencyId = 0;
        this.customerId = 0;
        this.lastUpdate = LocalDateTime.now();
    }

    public Balance(BigDecimal amount, long currencyId, long customerId, LocalDateTime lastUpdate) {
        this.amount = amount;
        this.currencyId = currencyId;
        this.customerId = customerId;
        this.lastUpdate = lastUpdate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Balance balance = (Balance) o;
        return currencyId == balance.currencyId &&
                customerId == balance.customerId &&
                Objects.equals(amount, balance.amount) &&
                Objects.equals(lastUpdate, balance.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), amount, currencyId, customerId, lastUpdate);
    }

    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", currencyId=" + currencyId +
                ", customerId=" + customerId +
                ", lastUpdate=" + lastUpdate +
                ", amount=" + amount +
                ", status=" + status +
                '}';

    }
}

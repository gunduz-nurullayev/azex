package az.test.domain;

import az.test.domain.enums.Status;

import java.util.Objects;

public class Currency extends BaseDomain {
    private String name;
    private int sortOrder;

    public Currency(String name, int sortOrder) {
        this.name = name;
        this.sortOrder = sortOrder;
    }

    public Currency(long id, String name, int sortOrder) {
        super(id, Status.ACTIVE);
        this.name = name;
        this.sortOrder = sortOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Currency currency = (Currency) o;
        return sortOrder == currency.sortOrder &&
                Objects.equals(name, currency.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, sortOrder);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", sortOrder=" + sortOrder +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}

package az.test.domain;

import az.test.domain.enums.Status;

import java.util.Objects;

public class BaseDomain {
    protected long id;
    protected Status status;

    public BaseDomain(long id, Status status) {
        this.id = id;
        this.status = status;
    }

    public BaseDomain() {
        this.id=01;
        this.status=Status.ACTIVE;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDomain that = (BaseDomain) o;
        return id == that.id &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }

    @Override
    public String toString() {
        return "BaseDomain{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}

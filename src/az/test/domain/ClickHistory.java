package az.test.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class ClickHistory extends BaseDomain implements Serializable {
    private static final long serialVersionUID = -3145118838752439263L;

    protected String token;
    protected long userId;
    protected LocalDateTime insertDate;
    protected String ip;

    public ClickHistory() {
        this.token="";
        this.userId=0;
        this.insertDate=LocalDateTime.now();
        this.ip="";
    }

    public ClickHistory(String token, long userId, LocalDateTime insertDate, String ip) {
        this.token = token;
        this.userId = userId;
        this.insertDate = insertDate;
        this.ip = ip;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClickHistory that = (ClickHistory) o;
        return userId == that.userId &&
                Objects.equals(token, that.token) &&
                Objects.equals(insertDate, that.insertDate) &&
                Objects.equals(ip, that.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), token, userId, insertDate, ip);
    }

    @Override
    public String toString() {
        return "ClickHistory{" +
                "id='" + id + '\'' +
                ", token='" + token  +
                ", userId='" + userId +
                ", insertDate=" + insertDate +
                ", ip='" + ip + '\'' +
                ", status=" + status +
                '}';
    }
}

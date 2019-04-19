package az.test.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class TokenHistory extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 4670612061733987411L;

    protected long user_id;
    protected String token;
    protected int token_type;
    protected LocalDateTime insertDate;
    protected LocalDateTime tokenExpireDate;

    public TokenHistory() {
    }

    public TokenHistory(long user_id, String token, int token_type, LocalDateTime insertDate, LocalDateTime tokenExpireDate) {
        this.user_id = user_id;
        this.token = token;
        this.token_type = token_type;
        this.insertDate = insertDate;
        this.tokenExpireDate = tokenExpireDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public LocalDateTime getTokenExpireDate() {
        return tokenExpireDate;
    }

    public void setTokenExpireDate(LocalDateTime tokenExpireDate) {
        this.tokenExpireDate = tokenExpireDate;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getToken_type() {
        return token_type;
    }

    public void setToken_type(int token_type) {
        this.token_type = token_type;
    }

    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TokenHistory that = (TokenHistory) o;
        return user_id == that.user_id &&
                token_type == that.token_type &&
                Objects.equals(token, that.token) &&
                Objects.equals(insertDate, that.insertDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user_id, token, token_type, insertDate);
    }

    @Override
    public String toString() {
        return "TokenHistory{" +
                "id=" + id +
                ", user_id='" + user_id +
                ", token='" + token + '\'' +
                ", token_type=" + token_type +
                ", insertDate=" + insertDate +
                ", status=" + status +
                '}';
    }
}

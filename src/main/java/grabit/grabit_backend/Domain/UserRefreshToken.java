package grabit.grabit_backend.Domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class UserRefreshToken {
    @Id
    @Column(name = "REFRESH_TOKEN_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshTokenSeq;

    @NotNull
    @Column(name = "USER_ID")
    private int userId;

    @NotNull
    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    public UserRefreshToken(int userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    public UserRefreshToken() {}

    public void setRefreshTokenSeq(Long refreshTokenSeq) { this.refreshTokenSeq = refreshTokenSeq; }
    public void setRefreshToken(String refresh_token) {
        this.refreshToken = refresh_token;
    }
    public void setUserId(int userId) { this.userId = userId; }

    public Long getRefreshTokenSeq() { return refreshTokenSeq; }
    public String getRefreshToken() { return refreshToken; }
    public int getUserId() { return userId; }
}

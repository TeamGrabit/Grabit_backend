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
    private String userId;

    @NotNull
    private String refreshToken;

    public UserRefreshToken(String userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    public void setRefreshToken(String refresh_token) {
        this.refreshToken = refresh_token;
    }

    public UserRefreshToken() {}
}

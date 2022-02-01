package grabit.grabit_backend.auth;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    @Value("${jwt.secret.key}")
    private String jwtSecret;
    private long tokenValidTime = 30 * 60 * 1000; // ms

    public String issueJwt(String user_email, String user_id, boolean isRefresh) {
        System.out.println("jwtSecret = " + jwtSecret);
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("grabit")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .claim("user_email", user_email)
                .claim("user_id", user_id)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }
}

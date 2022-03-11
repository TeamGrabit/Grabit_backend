package grabit.grabit_backend.repository;

import grabit.grabit_backend.domain.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    UserRefreshToken findByUserId(int userId);
    UserRefreshToken findByUserIdAndRefreshToken(int userId, String refreshToken);
}

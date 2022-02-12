package grabit.grabit_backend.Controller;

import grabit.grabit_backend.DTO.UserResDTO;
import grabit.grabit_backend.Domain.User;
import grabit.grabit_backend.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping("/")
    public ResponseEntity<UserResDTO> user(@AuthenticationPrincipal User user) {
        if (user == null) {
          throw new UnauthorizedException();
        }
        UserResDTO resDTO = new UserResDTO(user);
        return ResponseEntity.status(HttpStatus.OK).body(resDTO);
    }

}
package grabit.grabit_backend.controller;

import grabit.grabit_backend.dto.UserResDTO;
import grabit.grabit_backend.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping("")
    public ResponseEntity<UserResDTO> user(@AuthenticationPrincipal User user) {
        UserResDTO resDTO = new UserResDTO(user);
        return ResponseEntity.status(HttpStatus.OK).body(resDTO);
    }

}
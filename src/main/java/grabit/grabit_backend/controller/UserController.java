package grabit.grabit_backend.controller;

import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.dto.ResponseUserDTO;
import grabit.grabit_backend.dto.UpdateUserDTO;
import grabit.grabit_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("")
    public ResponseEntity<ResponseUserDTO> getUser(@AuthenticationPrincipal User user) {
        ResponseUserDTO resDTO = new ResponseUserDTO(user);
        return ResponseEntity.status(HttpStatus.OK).body(resDTO);
    }

    @PatchMapping("")
    public ResponseEntity<ResponseUserDTO> updateUser(@AuthenticationPrincipal User user,
                                                      @Valid @RequestBody UpdateUserDTO dto) {
        System.out.println(dto.getId());
        ResponseUserDTO resDTO = new ResponseUserDTO(userService.updateUser(dto, user));
        return ResponseEntity.status(HttpStatus.OK).body(resDTO);
    }

}
package grabit.grabit_backend.controller;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.dto.ResponseChallengeDTO;
import grabit.grabit_backend.dto.ResponsePagingDTO;
import grabit.grabit_backend.dto.ResponseUserDTO;
import grabit.grabit_backend.dto.UpdateUserDTO;
import grabit.grabit_backend.service.ChallengeService;
import grabit.grabit_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final ChallengeService challengeService;

    public UserController(UserService userService, ChallengeService challengeService) {
        this.userService = userService;
        this.challengeService = challengeService;
    }
    @GetMapping("")
    public ResponseEntity<ResponseUserDTO> getUser(@AuthenticationPrincipal User user) {
        ResponseUserDTO resDTO = new ResponseUserDTO(user);
        return ResponseEntity.status(HttpStatus.OK).body(resDTO);
    }

    @PatchMapping("")
    public ResponseEntity<ResponseUserDTO> updateUser(@AuthenticationPrincipal User user,
                                                      @Valid @RequestBody UpdateUserDTO dto) {
        ResponseUserDTO resDTO = new ResponseUserDTO(userService.updateUser(dto, user));
        return ResponseEntity.status(HttpStatus.OK).body(resDTO);
    }

    @GetMapping("challenges")
    public ResponseEntity<ResponsePagingDTO> getJoinedChallengeList(@AuthenticationPrincipal User user,
                                                                    @RequestParam(defaultValue = "1") Integer page,
                                                                    @RequestParam(defaultValue = "5") Integer size) {
        page = page - 1;
        Page<Challenge> challenges = challengeService.findUserJoinedChallengeList(user, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseChallengeDTO.convertPageDTO(challenges));
    }

}
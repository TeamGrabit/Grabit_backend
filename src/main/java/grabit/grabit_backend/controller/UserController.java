package grabit.grabit_backend.controller;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.domain.UserCommit;
import grabit.grabit_backend.dto.ResponseChallengePagingDTO;
import grabit.grabit_backend.dto.ResponseUserDTO;
import grabit.grabit_backend.dto.UpdateUserDTO;
import grabit.grabit_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
        ResponseUserDTO resDTO = new ResponseUserDTO(userService.updateUser(dto, user));
        return ResponseEntity.status(HttpStatus.OK).body(resDTO);
    }

    @GetMapping("challenges")
    public ResponseEntity<ResponseChallengePagingDTO> getJoinedChallengeList(@AuthenticationPrincipal User user,
                                                                             @RequestParam(defaultValue = "1") Integer page,
                                                                             @RequestParam(defaultValue = "5") Integer size) {
        if (page < 1) page = 1;
        page = page - 1;
        Page<Challenge> challenges = userService.findUserJoinedChallenges(user, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseChallengePagingDTO.convertDTO(challenges));
    }

    @GetMapping("commits")
    public ResponseEntity<List<UserCommit>> getUserCommits(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getCommitData(user));
    }

}
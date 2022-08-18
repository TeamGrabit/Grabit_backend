package grabit.grabit_backend.controller;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.JoinChallengeRequest;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.domain.UserChallenge;
import grabit.grabit_backend.enums.SearchType;
import grabit.grabit_backend.exception.UnauthorizedException;
import grabit.grabit_backend.repository.ChallengeSearchRepository;
import grabit.grabit_backend.repository.ChallengeSearchWithDesc;
import grabit.grabit_backend.repository.ChallengeSearchWithLeader;
import grabit.grabit_backend.repository.ChallengeSearchWithTitle;
import grabit.grabit_backend.repository.ChallengeSearchWithTitleAndDesc;
import grabit.grabit_backend.dto.*;
import grabit.grabit_backend.exception.DuplicateDataException;
import grabit.grabit_backend.service.ChallengeService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("challenges")
public class ChallengeController {

    private final ChallengeService challengeService;

	public ChallengeController(ChallengeService challengeService) {
		this.challengeService = challengeService;
	}

    /**
     * 챌린지 목록 조회 (search) with Paing API
     *
     * @param page
     * @param size
     * @param title
     * @param description
     * @param leaderId
     * @return
     */
    @GetMapping(value = "")
    public ResponseEntity<ResponsePagingDTO> findAllChallengesWithPageAPI(@RequestParam(defaultValue = "1") Integer page,
                                                                          @RequestParam(defaultValue = "5") Integer size,
                                                                          @Valid @RequestBody SearchChallengeDTO searchChallengeDTO) {
        page = page - 1;
        Page<Challenge> findChallengesWithPage = challengeService.findChallengeBySearchWithPage(searchChallengeDTO, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseChallengePagingDTO.convertDTO(findChallengesWithPage));
    }

    /**
     * 챌린지 생성 API
     *
     * @param createChallengeDTO
     * @param user
     * @return responseEntity
     */
    @PostMapping(value = "")
    public ResponseEntity<ResponseChallengeDTO> createChallengeAPI(@Valid @RequestBody CreateChallengeDTO createChallengeDTO,
                                                                   @AuthenticationPrincipal User user) {
        Challenge createdChallenge = challengeService.createChallenge(createChallengeDTO, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseChallengeDTO.convertDTO(createdChallenge));
    }

    /**
     * 챌린지 수정 API
     *
     * @param id
     * @param modifyChallengeDTO
     * @param user
     * @return responseEntity
     */
    @PatchMapping(value = "{id}")
    public ResponseEntity<ResponseChallengeDTO> updateChallengeAPI(@PathVariable(value = "id") Long id,
                                                                   @Valid @RequestBody ModifyChallengeDTO modifyChallengeDTO,
                                                                   @AuthenticationPrincipal User user) {
        Challenge modifiedChallenge = challengeService.updateChallenge(id, modifyChallengeDTO, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseChallengeDTO.convertDTO(modifiedChallenge));
    }

    /**
     * 챌린지 삭제 API
     *
     * @param id
     * @param user
     * @return responseEntity
     */
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteChallengeAPI(@PathVariable(value = "id") Long id,
                                                   @AuthenticationPrincipal User user) {
        challengeService.deleteChallengeById(id, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 챌린지 정보 조회 API
     *
     * @param id
     * @return
     */
    @GetMapping(value = "{id}")
    public ResponseEntity<ResponseChallengeDTO> findChallengeAPI(@PathVariable(value = "id") Long id,
                                                                 @AuthenticationPrincipal User user) {
        Challenge challenge = challengeService.findChallengeByIdWithAuth(id, user);
        return ResponseEntity.status(HttpStatus.OK).
                body(ResponseChallengeDTO.convertDTO(challenge));
    }

    /**
     * 챌린지 가입 요청 API
     *
     * @param id
     * @param user
     * @return
     */
    @PostMapping(value = "{id}/join")
    public ResponseEntity<ResponseChallengeDTO> joinChallengeAPI(@PathVariable(value = "id") Long id,
                                                                 @AuthenticationPrincipal User user) {
        try {
            Challenge challenge = challengeService.requestJoinChallenge(id, user);
            return challenge == null ? null :
                    ResponseEntity.status(HttpStatus.OK).
                            body(ResponseChallengeDTO.convertDTO(challenge));

        } catch (DataIntegrityViolationException e) {
            throw new DuplicateDataException("이미 가입신청이 되어있습니다.");
        }
    }

    /**
     * 챌린지 탈퇴 API
     *
     * @param id
     * @param user
     * @return
     */
    @PatchMapping(value = "{id}/leave")
    public ResponseEntity<Void> leaveChallengeAPI(@PathVariable(value = "id") Long id,
                                                  @AuthenticationPrincipal User user) {
        challengeService.leaveChallenge(id, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 챌린지 가입 요청 승인 API
     */
    @PostMapping(value = "join/approve")
    public ResponseEntity<ResponseChallengeDTO> approveJoinChallengeAPI(@RequestParam() Long requestId,
                                        @AuthenticationPrincipal User user) {
        Challenge challenge = challengeService.approveJoinChallengeRequest(requestId, user);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseChallengeDTO.convertDTO(challenge));
    }

    /**
     * 챌린지 가입 요청 거절 API
     */
    @PostMapping(value = "join/reject")
    public void rejectJoinChallengeAPI(@RequestParam() Long requestId,
                                       @AuthenticationPrincipal User user) {
        challengeService.rejectJoinChallengeRequest(requestId, user);
    }

    /**
     * 챌린지 가입 요청 목록 조회 API
     */
    @GetMapping(value = "join")
    public ResponseEntity<ResponseJoinChallengeRequestPagingDTO> findJoinChallengeRequestsWithPageAPI(@RequestParam() Long challengeId,
                                                                     @RequestParam(defaultValue = "1") Integer page,
                                                                     @RequestParam(defaultValue = "5") Integer size,
                                                                     @AuthenticationPrincipal User user) {
        page -= 1;
        Page<JoinChallengeRequest> joinChallengeRequestListByChallengeWithPage = challengeService.findJoinChallengeRequestListByChallengeWithPage(user, challengeId, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseJoinChallengeRequestPagingDTO.convertDTO(joinChallengeRequestListByChallengeWithPage));
    }
}


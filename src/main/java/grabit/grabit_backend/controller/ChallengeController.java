package grabit.grabit_backend.controller;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.dto.CreateChallengeDTO;
import grabit.grabit_backend.dto.ModifyChallengeDTO;
import grabit.grabit_backend.dto.ResponseChallengeDTO;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/challenges")
public class ChallengeController {

	private final ChallengeService challengeService;

	@Autowired
	public ChallengeController(ChallengeService challengeService) {
		this.challengeService = challengeService;
	}

	/**
	 * 챌린지 검색 API with Paging
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(value = "")
	public ResponseEntity<Page<Challenge>> findAllChallengesWithPageAPI(@RequestParam(defaultValue = "0") Integer page,
																		@RequestParam(defaultValue = "5") Integer size){
		Page<Challenge> findChallengesWithPage = challengeService.findAllChallengeWithPage(page, size);
		return ResponseEntity.status(HttpStatus.OK).body(findChallengesWithPage);
	}

	/**
	 * 챌린지 생성 API
	 * @param createChallengeDTO
	 * @param user
	 * @return responseEntity
	 */
	@PostMapping(value = "")
	public ResponseEntity<ResponseChallengeDTO> createChallengeAPI(@Valid @RequestBody CreateChallengeDTO createChallengeDTO,
																   @AuthenticationPrincipal User user){
		ResponseChallengeDTO responseChallengeDTO = challengeService.createChallenge(createChallengeDTO, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseChallengeDTO);
	}

	/**
	 * 챌린지 수정 API
	 * @param id
	 * @param modifyChallengeDTO
	 * @param user
	 * @return responseEntity
	 */
	@PatchMapping(value = "{id}")
	public ResponseEntity<ResponseChallengeDTO> updateChallengeAPI(@PathVariable(value = "id") Long id,
																   @Valid @RequestBody ModifyChallengeDTO modifyChallengeDTO,
																   @AuthenticationPrincipal User user){
		ResponseChallengeDTO responseChallengeDTO = challengeService.updateChallenge(id, modifyChallengeDTO, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseChallengeDTO);
	}

	/**
	 * 챌린지 삭제 API
	 * @param id
	 * @param user
	 * @return responseEntity
	 */
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> deleteChallengeAPI(@PathVariable(value = "id") Long id,
												   @AuthenticationPrincipal User user){
		challengeService.deleteChallengeById(id, user);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	/**
	 * 챌린지 검색 API
	 * @param id
	 * @return responseEntity
	 */
	@GetMapping(value = "{id}")
	public ResponseEntity<ResponseChallengeDTO> findChallengeAPI(@PathVariable(value = "id") Long id){
		ResponseChallengeDTO responseChallengeDTO = challengeService.findChallengeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(responseChallengeDTO);
	}

	/**
	 * 챌린지 가입 API
	 * @param id
	 * @param user
	 * @return
	 */
	@PatchMapping(value = "{id}/join")
	public ResponseEntity<ResponseChallengeDTO> joinChallengeAPI(@PathVariable(value = "id") Long id,
																 @AuthenticationPrincipal User user){
		ResponseChallengeDTO responseChallengeDTO = challengeService.joinChallenge(id, user);

		return ResponseEntity.status(HttpStatus.OK).body(responseChallengeDTO);
	}

	/**
	 * 챌린지 탈퇴 API
	 * @param id
	 * @param user
	 * @return
	 */
	@PatchMapping(value = "{id}/leave")
	public ResponseEntity<Void> leaveChallengeAPI(@PathVariable(value = "id") Long id,
												  @AuthenticationPrincipal User user){
		challengeService.leaveChallenge(id, user);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}

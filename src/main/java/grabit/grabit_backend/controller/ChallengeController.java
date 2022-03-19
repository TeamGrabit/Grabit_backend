package grabit.grabit_backend.controller;

import grabit.grabit_backend.dto.CreateChallengeDTO;
import grabit.grabit_backend.dto.ModifyChallengeDTO;
import grabit.grabit_backend.dto.ResponseChallengeDTO;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("challenges")
public class ChallengeController {

	private final ChallengeService challengeService;

	@Autowired
	public ChallengeController(ChallengeService challengeService) {
		this.challengeService = challengeService;
	}

	/**
	 * 모든 챌린지 검색 API
	 * @return List of Challenge
	 */
	@GetMapping(value = "")
	public ResponseEntity<ArrayList<ResponseChallengeDTO>> findAllChallengesAPI(){
		ArrayList<ResponseChallengeDTO> findChallenges = challengeService.findAllChallenge();
		return ResponseEntity.status(HttpStatus.OK).body(findChallenges);
	}

	/**
	 * 챌린지 생성 API
	 * @param createChallengeDTO
	 * @return responseChallengeDTO
	 */
	@PostMapping(value = "")
	public ResponseEntity<ResponseChallengeDTO> createChallengeAPI(@Valid @RequestBody CreateChallengeDTO createChallengeDTO, @AuthenticationPrincipal User user){
		ResponseChallengeDTO responseChallengeDTO = challengeService.createChallenge(createChallengeDTO, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseChallengeDTO);
	}

	/**
	 * 챌린지 수정 API
	 * @param id
	 * @param modifyChallengeDTO
	 * @return responseChallengeDTO
	 */
	@PatchMapping(value = "{id}")
	public ResponseEntity<ResponseChallengeDTO> updateChallengeAPI(@PathVariable(value = "id") Long id,
										@Valid @RequestBody ModifyChallengeDTO modifyChallengeDTO, @AuthenticationPrincipal User user){
		ResponseChallengeDTO responseChallengeDTO = challengeService.updateChallenge(id, modifyChallengeDTO, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseChallengeDTO);
	}

	/**
	 * 챌린지 삭제 API
	 * @param id
	 */
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> deleteChallengeAPI(@PathVariable(value = "id") Long id, @AuthenticationPrincipal User user){
		challengeService.deleteChallengeById(id, user);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	/**
	 * 챌린지 검색 API
	 * @param id
	 * @return responseChallengeDTO
	 */
	@GetMapping(value = "{id}")
	public ResponseEntity<ResponseChallengeDTO> findChallengeAPI(@PathVariable(value = "id") Long id){
		ResponseChallengeDTO responseChallengeDTO = challengeService.findChallengeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(responseChallengeDTO);
	}

	@PatchMapping(value = "{id}/join")
	public ResponseEntity<ResponseChallengeDTO> joinChallengeAPI(@PathVariable(value = "id") Long id, @AuthenticationPrincipal User user){
		ResponseChallengeDTO responseChallengeDTO = challengeService.joinChallenge(id, user);
		return ResponseEntity.status(HttpStatus.OK).body(responseChallengeDTO);
	}

	@PatchMapping(value = "{id}/leave")
	public ResponseEntity<ResponseChallengeDTO> leaveChallengeAPI(@PathVariable(value = "id") Long id, @AuthenticationPrincipal User user){
		ResponseChallengeDTO responseChallengeDTO = challengeService.leaveChallenge(id, user);
		return ResponseEntity.status(HttpStatus.OK).body(responseChallengeDTO);
	}
}

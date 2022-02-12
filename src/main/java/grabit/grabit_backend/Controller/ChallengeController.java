package grabit.grabit_backend.Controller;

import grabit.grabit_backend.DTO.ChallengeDTO;
import grabit.grabit_backend.Domain.Challenge;
import grabit.grabit_backend.Service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/challenges")
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
	public ResponseEntity<List<ChallengeDTO>> findAllChallengesAPI(){
		List<Challenge> findChallenges = challengeService.findAllChallenge();
		ArrayList<ChallengeDTO> findChallengesDTO = new ArrayList<>();
		for(Challenge challenge: findChallenges){
			findChallengesDTO.add(new ChallengeDTO(challenge));
		}
		return ResponseEntity.status(HttpStatus.OK).body(findChallengesDTO);
	}

	/**
	 * 챌린지 생성 API
	 * @param challengeDTO
	 * @return Challenge
	 */
	@PostMapping(value = "")
	public ResponseEntity<ChallengeDTO> createChallengeAPI(@RequestBody ChallengeDTO challengeDTO){
		Challenge challenge = new Challenge("testId", challengeDTO.getName());
		Long challengeId = challengeService.createChallenge(challenge);
		ChallengeDTO createdChallenge = new ChallengeDTO(challengeService.findChallengeById(challengeId));
		return ResponseEntity.status(HttpStatus.CREATED).body(createdChallenge);
	}

	/**
	 * 챌린지 수정 API
	 * @param id
	 * @param challengeDTO
	 * @return Challenge
	 */
	@PatchMapping(value = "/{id}")
	public ResponseEntity<ChallengeDTO> updateChallengeAPI(@PathVariable(value = "id") Long id,
										@RequestBody ChallengeDTO challengeDTO){
		Challenge challenge = challengeService.findChallengeById(id);
		challenge.setName(challengeDTO.getName());
		challenge.setLeaderId(challengeDTO.getLeaderId());
		ChallengeDTO changeChallenge = new ChallengeDTO(challengeService.updateChallenge(id, challenge));
		return ResponseEntity.status(HttpStatus.CREATED).body(changeChallenge);
	}

	/**
	 * 챌린지 삭제 API
	 * @param id
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteChallengeAPI(@PathVariable(value = "id") Long id){
		challengeService.deleteChallengeById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	/**
	 * 챌린지 검색 API
	 * @param id
	 * @return Challenge
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChallengeDTO> findChallengeAPI(@PathVariable(value = "id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(new ChallengeDTO(challengeService.findChallengeById(id)));
	}
}

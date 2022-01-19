package grabit.grabit_backend.Controller;

import grabit.grabit_backend.DTO.ChallengeDTO;
import grabit.grabit_backend.Domain.Challenge;
import grabit.grabit_backend.Service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
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
	public List<Challenge> findAllChallengesAPI(){
		return challengeService.findAllChallenge();
	}

	/**
	 * 챌린지 생성 API
	 * @param challengeDTO
	 * @return Challenge
	 */
	@PostMapping(value = "")
	public Long createChallengeAPI(@RequestBody ChallengeDTO challengeDTO){
		Challenge challenge = new Challenge("testId", challengeDTO.getName());
		return challengeService.createChallenge(challenge);
	}

	/**
	 * 챌린지 수정 API
	 * @param id
	 * @param challengeDTO
	 * @return Challenge
	 */
	@PatchMapping(value = "/{id}")
	public Challenge updateChallengeAPI(@PathVariable(value = "id") Long id,
										@RequestBody ChallengeDTO challengeDTO){
		Challenge challenge = challengeService.findChallengeById(id);
		challenge.setName(challengeDTO.getName());
		challenge.setLeaderId(challengeDTO.getLeaderId());
		return challengeService.updateChallenge(id, challenge);
	}

	/**
	 * 챌린지 삭제 API
	 * @param id
	 */
	@DeleteMapping(value = "/{id}")
	public void deleteChallengeAPI(@PathVariable(value = "id") Long id){
		challengeService.deleteChallengeById(id);
	}




}

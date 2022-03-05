package grabit.grabit_backend.Controller;

import grabit.grabit_backend.DTO.ChallengeDTO;
import grabit.grabit_backend.DTO.CreateChallengeDTO;
import grabit.grabit_backend.DTO.FindChallengeDTO;
import grabit.grabit_backend.DTO.ModifyChallengeDTO;
import grabit.grabit_backend.DTO.ResponseChallengeDTO;
import grabit.grabit_backend.Domain.Challenge;
import grabit.grabit_backend.Service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
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
	public ResponseEntity<ResponseChallengeDTO> createChallengeAPI(@Valid @RequestBody CreateChallengeDTO createChallengeDTO){
		ResponseChallengeDTO responseChallengeDTO = challengeService.createChallenge(createChallengeDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseChallengeDTO);
	}

	/**
	 * 챌린지 수정 API
	 * @param id
	 * @param modifyChallengeDTO
	 * @return responseChallengeDTO
	 */
	@PatchMapping(value = "/{id}")
	public ResponseEntity<ResponseChallengeDTO> updateChallengeAPI(@PathVariable(value = "id") Long id,
										@Valid @RequestBody ModifyChallengeDTO modifyChallengeDTO){
		ResponseChallengeDTO responseChallengeDTO = challengeService.updateChallenge(modifyChallengeDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseChallengeDTO);
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
	 * @return responseChallengeDTO
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseChallengeDTO> findChallengeAPI(@PathVariable(value = "id") Long id){
		ResponseChallengeDTO responseChallengeDTO = challengeService.findChallengeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(responseChallengeDTO);
	}
}

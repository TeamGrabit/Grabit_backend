package grabit.grabit_backend.Controller;

import grabit.grabit_backend.Domain.Challenge;
import grabit.grabit_backend.Service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("test")
public class TestController {

	@Autowired
	ChallengeService challengeService;

	@GetMapping(value = "/save")
	public Long save(){
		Challenge challenge = new Challenge("testId", "test");
		return challengeService.makeChallenge(challenge);
	}

	@GetMapping(value = "/del")
	public Long delete(){
		Challenge challenge = new Challenge("testId", "test");
		challengeService.deleteChallengeById(challenge.getId());
		return challenge.getId();
	}

}

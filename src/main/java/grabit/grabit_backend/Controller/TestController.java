package grabit.grabit_backend.Controller;

import grabit.grabit_backend.Domain.Challenge;
import grabit.grabit_backend.Service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("test")
public class TestController {

	@Autowired
	ChallengeService challengeService;

	@GetMapping(value = "/save")
	public Challenge save(){
		Challenge challenge = new Challenge();
		challenge.setName("테스트");
		challenge.setUser_id("test");
		challenge.setCreatedAt(LocalDateTime.now());
		challengeService.save(challenge);
		return challenge;
	}

}

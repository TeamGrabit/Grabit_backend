package grabit.grabit_backend.Service;

import grabit.grabit_backend.Domain.Challenge;
import grabit.grabit_backend.Repository.ChallengeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ChallengeServiceTest {
	@Autowired
	ChallengeService challengeService;
	@Autowired
	ChallengeRepository challengeRepository;

	@Test
	void save() {
		//given
		Challenge challenge = new Challenge("testId", "test");

		//when
		Long id = challengeService.makeChallenge(challenge);

		//then
		Optional<Challenge> findChallenge = challengeRepository.findById(id);
		assertEquals(id, findChallenge.get().getId());
	}
}
package grabit.grabit_backend.service;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.repository.ChallengeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
class ChallengeServiceTest {
	@Mock
	ChallengeRepository challengeRepository;
	@InjectMocks
	ChallengeService challengeService;

	@Test
	void 챌린지_생성() {
		//given
		Challenge challenge = new Challenge();
		when(challengeRepository.save(challenge)).thenReturn(challenge);

		//when
		//Long id = challengeService.createChallenge(challenge);

		//then
		assertEquals(id, challenge.getId());
	}

	@Test
	void 챌린지_삭제_id(){
		//given
		Challenge challenge = new Challenge();
		when(challengeRepository.findById(challenge.getId())).thenReturn(Optional.ofNullable(challenge));

		//when
		challengeService.deleteChallengeById(challenge.getId());

		//then
		verify(challengeRepository).deleteById(challenge.getId());
	}

	@Test
	void 챌린지_삭제_아이디없음_에러(){
		//given
		Challenge challenge = new Challenge();
		when(challengeRepository.findById(challenge.getId())).thenReturn(Optional.ofNullable(null));

		//when
		Throwable exception = assertThrows(IllegalStateException.class, () -> {
			challengeService.deleteChallengeById(challenge.getId());
		});

		//then
		assertEquals("존재하지 않는 챌린지입니다..", exception.getMessage());
	}

	@Test
	void 챌린지_검색_id(){
		//given
		Challenge challenge = new Challenge();
		when(challengeRepository.findById(challenge.getId())).thenReturn(Optional.ofNullable(challenge));

		//when
		//Challenge findChallenge = challengeService.findChallengeById();

		//then
		//assertEquals(findChallenge.getId(), challenge.getId());
	}

	@Test
	void 챌린지_검색_name(){
		//given
		Challenge challenge1 = new Challenge();
		Challenge challenge2 = new Challenge();
		when(challengeRepository.findByName(challenge1.getName())).thenReturn(new ArrayList<Challenge>(Arrays.asList(challenge1, challenge2)));

		//when
		List<Challenge> findChallenge = challengeService.findChallengeByName(challenge1.getName());

		//then
		assertEquals(Arrays.asList(challenge1, challenge2), findChallenge);
	}

	@Test
	void 챌린지_수정(){
		//given
		Challenge challenge = new Challenge();
		when(challengeRepository.findById(challenge.getId())).thenReturn(Optional.ofNullable(challenge));
		when(challengeRepository.save(challenge)).thenReturn(challenge);

		//when
		challenge.setName("changeName");
		// Challenge changeChallenge = challengeService.updateChallenge(challenge.getId(), challenge);

		//then
		// assertEquals(challenge, changeChallenge);
	}

	@Test
	void 챌린지_수정_아이디없음_에러(){
		//given
		Challenge challenge = new Challenge();
		when(challengeRepository.findById(challenge.getId())).thenReturn(Optional.ofNullable(null));

		//when
		challenge.setName("이름 바꾸기 테스트");
		Throwable exception = assertThrows(IllegalStateException.class, () -> {
			challengeService.updateChallenge(challenge.getId(), challenge);
		});

		//then
		assertEquals("존재하지 않는 챌린지입니다..", exception.getMessage());

	}
}
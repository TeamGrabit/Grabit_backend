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
	@InjectMocks
	ChallengeService challengeService;
	@Mock
	ChallengeRepository challengeRepository;

	@Test
	void 챌린지_생성() {
		//given
		Challenge challenge = new Challenge("testId", "test");
		when(challengeRepository.save(challenge)).thenReturn(challenge);

		//when
		Long id = challengeService.makeChallenge(challenge);

		//then
		assertEquals(id, challenge.getId());
	}

	@Test
	void 챌린지_삭제_id(){
		//given
		Challenge challenge = new Challenge("testId", "test");
		when(challengeRepository.findById(challenge.getId())).thenReturn(Optional.ofNullable(challenge));

		//when
		challengeService.deleteChallengeById(challenge.getId());

		//then
		verify(challengeRepository).deleteById(challenge.getId());
	}

	@Test
	void 챌린지_삭제_아이디없음_에러(){
		//given
		Challenge challenge = new Challenge("testId", "test");
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
		Challenge challenge = new Challenge("testId", "test");
		when(challengeRepository.findById(challenge.getId())).thenReturn(Optional.ofNullable(challenge));

		//when
		Optional<Challenge> findChallenge = challengeService.findChallengeById(challenge.getId());

		//then
		assertEquals(findChallenge.get().getId(), challenge.getId());
	}

	@Test
	void 챌린지_검색_name(){
		//given
		Challenge challenge1 = new Challenge("testId1", "test");
		Challenge challenge2 = new Challenge("testId2", "test");
		when(challengeRepository.findByName(challenge1.getName())).thenReturn(new ArrayList<Challenge>(Arrays.asList(challenge1, challenge2)));

		//when
		List<Challenge> findChallenge = challengeService.findChallengeByName(challenge1.getName());

		//then
		assertEquals(Arrays.asList(challenge1, challenge2), findChallenge);
	}

	@Test
	void 챌린지_수정(){
		//given
		Challenge challenge = new Challenge("testId", "test");
		when(challengeRepository.findById(challenge.getId())).thenReturn(Optional.ofNullable(challenge));
		when(challengeRepository.save(challenge)).thenReturn(challenge);

		//when
		challenge.setName("changeName");
		challenge.setLeaderId("changeLeader");
		Optional<Challenge> changeChallenge = challengeService.updateChallenge(challenge.getId(), challenge);

		//then
		assertEquals(challenge, changeChallenge.get());
	}

	@Test
	void 챌린지_수정_아이디없음_에러(){
		//given
		Challenge challenge = new Challenge("testId", "test");
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
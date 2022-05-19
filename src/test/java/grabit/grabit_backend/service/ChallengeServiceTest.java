package grabit.grabit_backend.service;

import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.domain.UserChallenge;
import grabit.grabit_backend.dto.CreateChallengeDTO;
import grabit.grabit_backend.repository.ChallengeRepository;
import grabit.grabit_backend.repository.UserChallengeRepository;
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
	@Mock
	UserChallengeRepository userChallengeRepository;
	@InjectMocks
	ChallengeService challengeService;

	@Test
	void 챌린지_생성() {
		//given
		CreateChallengeDTO createChallengeDTO = CreateChallengeDTO.builder()
				.name("챌린지 생성 테스트")
				.description("챌린지 생성 테스트 설명")
				.isPrivate(false)
				.build();
		User user = new User();
		user.setUserId("testId");
		user.setUsername("testName");
		user.setId(1);
		Challenge challenge = Challenge.createChallenge(createChallengeDTO, user);
		UserChallenge userChallenge = UserChallenge.createUserChallenge(challenge, user);
		doReturn(challenge).when(challengeRepository).save(any(Challenge.class));
		doReturn(userChallenge).when(userChallengeRepository).save(any(UserChallenge.class));

		//when
		Challenge createChallenge = challengeService.createChallenge(createChallengeDTO, user);

		//then
		assertEquals(challenge.getName(), createChallenge.getName());
		assertEquals(challenge.getDescription(), createChallenge.getDescription());
		assertEquals(challenge.getIsPrivate(), createChallenge.getIsPrivate());
		assertEquals(challenge.getLeader().getUserId(), createChallenge.getLeader().getUserId());
		verify(challengeRepository).save(any(Challenge.class));
		verify(userChallengeRepository).save(any(UserChallenge.class));
	}

	@Test
	void 챌린지_삭제_id() {

	}

	@Test
	void 챌린지_삭제_아이디없음_에러() {

	}

	@Test
	void 챌린지_검색_id() {

	}

	@Test
	void 챌린지_검색_name() {

	}

	@Test
	void 챌린지_수정() {

	}

	@Test
	void 챌린지_수정_아이디없음_에러() {

	}
}
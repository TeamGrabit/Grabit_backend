package grabit.grabit_backend.service;

import grabit.grabit_backend.GrabitBackendApplication;
import grabit.grabit_backend.auth.CustomUserDetailService;
import grabit.grabit_backend.auth.JwtProvider;
import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.domain.UserChallenge;
import grabit.grabit_backend.dto.CreateChallengeDTO;
import grabit.grabit_backend.repository.ChallengeRepository;
import grabit.grabit_backend.repository.UserChallengeRepository;
import grabit.grabit_backend.repository.UserRepository;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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

	@Mock
	UserRepository userRepository;
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

	@Test
	void 테스트() {
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJncmFiaXQiLCJpYXQiOjE2NTgwMzY2MjksImV4cCI6MTY1ODA3OTgyOSwic3ViIjoiMTAxMDgwOTEzIiwidXNlcl9pZCI6MTAxMDgwOTEzfQ.Sr0cziP0ZUI4gLs2LeN5e4sYROMowrl9dnRHjKn-NXA";
		CustomUserDetailService customUserDetailService = new CustomUserDetailService(userRepository);
		JwtProvider jwtProvider = new JwtProvider(customUserDetailService);
		if(token != null && jwtProvider.validateToken(token)){
			UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtProvider.getUserPk(token));
			System.out.println(token);
			System.out.println(userDetails.getUsername());
			System.out.println(userDetails.getPassword());
			System.out.println(userDetails.getAuthorities());
			System.out.println(userDetails.getUsername());
		}
	}
}
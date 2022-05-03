package grabit.grabit_backend.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import grabit.grabit_backend.domain.Challenge;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static grabit.grabit_backend.domain.QChallenge.*;
import static grabit.grabit_backend.domain.QUser.*;
import static grabit.grabit_backend.domain.QUserChallenge.*;

@Repository
public class ChallengeCustomRepositoryImpl implements ChallengeCustomRepository{

	private final JPAQueryFactory jpaQueryFactory;

	public ChallengeCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory){
		this.jpaQueryFactory = jpaQueryFactory;
	}

	@Override
	public Optional<Challenge> findChallengeById(Long id) {
		return Optional.ofNullable(jpaQueryFactory
				.selectFrom(challenge)
				.join(challenge.userChallengeList, userChallenge).fetchJoin()
				.join(userChallenge.user, user).fetchJoin()
				.where(challenge.id.eq(id))
				.fetchOne()
		);
	}

	@Override
	public List<Challenge> findAllChallengeWithPaging(Pageable pageable) {
		return jpaQueryFactory
				.selectFrom(challenge)
				.join(challenge.userChallengeList, userChallenge).fetchJoin()
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
	}
}

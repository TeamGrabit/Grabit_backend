package grabit.grabit_backend.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static grabit.grabit_backend.domain.QChallenge.challenge;
import static grabit.grabit_backend.domain.QUser.user;
import static grabit.grabit_backend.domain.QUserChallenge.userChallenge;

@Repository
public class ChallengeCustomRepositoryImpl implements ChallengeCustomRepository{

	private final JPAQueryFactory jpaQueryFactory;

	public ChallengeCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory){
		this.jpaQueryFactory = jpaQueryFactory;
	}
	@PersistenceContext
	private EntityManager entityManager;
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
	public Page<Challenge> findAllChallengeWithPaging(Pageable pageable) {
		List<Challenge> content = jpaQueryFactory
				.selectFrom(challenge)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		JPAQuery<Challenge> countQuery = jpaQueryFactory
				.selectFrom(challenge);

		return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetch().size());
	}

	@Override
	public Page<Challenge> findUserJoinedChallengeList(Pageable pageable, User u) {
		List<Challenge> challengeList = jpaQueryFactory
				.selectFrom(challenge)
				.join(challenge.userChallengeList, userChallenge).fetchJoin()
				.where(userChallenge.user.eq(u))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize()).fetch();

		JPAQuery<Challenge> query = jpaQueryFactory.selectFrom(challenge)
				.join(challenge.userChallengeList, userChallenge).fetchJoin()
				.where(userChallenge.user.eq(u));

		return PageableExecutionUtils.getPage(challengeList, pageable, () -> query.fetch().size());
	}
}

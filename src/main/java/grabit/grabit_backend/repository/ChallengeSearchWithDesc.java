package grabit.grabit_backend.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import grabit.grabit_backend.domain.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static grabit.grabit_backend.domain.QChallenge.*;

@Repository
public class ChallengeSearchWithDesc implements ChallengeSearchRepository{

	private final JPAQueryFactory jpaQueryFactory;

	public ChallengeSearchWithDesc(JPAQueryFactory jpaQueryFactory){
		this.jpaQueryFactory = jpaQueryFactory;
	}

	@Override
	public Page<Challenge> findChallengeWithPaing(Pageable pageable, String content) {
		List<Challenge> challenges = jpaQueryFactory
				.selectFrom(challenge)
				.where(challenge.description.contains(content))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.orderBy(challenge.createdAt.desc())
				.fetch();

		JPAQuery<Challenge> countQuery = jpaQueryFactory
				.selectFrom(challenge)
				.where(challenge.description.contains(content));

		return PageableExecutionUtils.getPage(challenges, pageable, () -> countQuery.fetch().size());
	}
}

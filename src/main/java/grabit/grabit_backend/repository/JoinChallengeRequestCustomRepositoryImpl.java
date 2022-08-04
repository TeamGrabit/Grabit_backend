package grabit.grabit_backend.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import grabit.grabit_backend.domain.Challenge;
import grabit.grabit_backend.domain.JoinChallengeRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static grabit.grabit_backend.domain.QJoinChallengeRequest.joinChallengeRequest;

@Repository
public class JoinChallengeRequestCustomRepositoryImpl implements JoinChallengeRequestCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public JoinChallengeRequestCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<JoinChallengeRequest> findJoinChallengeRequestByChallengeWithPage(Pageable pageable, Challenge c) {
        List<JoinChallengeRequest> content = jpaQueryFactory
                .selectFrom(joinChallengeRequest)
                .where(joinChallengeRequest.challenge.eq(c))
                .limit(pageable.getPageSize())
                .orderBy(joinChallengeRequest.createdAt.asc()).fetch(); // 오래된 순

        JPAQuery<JoinChallengeRequest> countQuery = jpaQueryFactory
                .selectFrom(joinChallengeRequest)
                .where(joinChallengeRequest.challenge.eq(c));
        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetch().size());
    }
}

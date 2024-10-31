package kgu.developers.domain.comment.infrastructure;

import static kgu.developers.domain.comment.domain.QComment.*;

import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryCommentRepository {

	private final JPAQueryFactory queryFactory;

	public void deleteAllByDeletedAtBefore(int retentionDays) {
		LocalDateTime thresholdDate = LocalDateTime.now().minusDays(retentionDays);

		queryFactory.delete(comment)
			.where(comment.deletedAt.isNotNull().and(comment.deletedAt.before(thresholdDate)))
			.execute();
	}
}

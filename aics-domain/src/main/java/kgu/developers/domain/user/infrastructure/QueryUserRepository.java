package kgu.developers.domain.user.infrastructure;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kgu.developers.common.response.PageableResponse;
import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryUserRepository {
	private final JPAQueryFactory queryFactory;

	public PaginatedListResponse<User> findAllByNameOrderByIdDesc(Pageable pageable, String name) {
		QUserJpaEntity user = QUserJpaEntity.userJpaEntity;

		BooleanExpression whereClause = user.deletedAt.isNull()
			.and(name != null ? user.name.contains(name) : null);

		List<UserJpaEntity> userEntities = queryFactory.select(user)
			.from(user)
			.where(whereClause)
			.orderBy(user.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		List<User> users = userEntities.stream()
			.map(UserJpaEntity::toDomain)
			.toList();

		List<String> userIds = queryFactory.select(user.id)
			.from(user)
			.where(whereClause)
			.orderBy(user.id.desc())
			.fetch();

		return PaginatedListResponse.of(users, PageableResponse.of(pageable, userIds));
	}

	public void deleteAllByDeletedAtBefore(int retentionDays) {
		QUserJpaEntity user = QUserJpaEntity.userJpaEntity;
		LocalDateTime thresholdDate = LocalDateTime.now().minusDays(retentionDays);

		queryFactory.delete(user)
			.where(user.deletedAt.isNotNull().and(user.deletedAt.before(thresholdDate)))
			.execute();
	}
}

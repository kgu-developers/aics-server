package kgu.developers.domain.user.infrastructure;

import static kgu.developers.domain.user.domain.QUser.user;

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

	public PaginatedListResponse findAllByNameOrderByIdDesc(Pageable pageable, String name) {
		BooleanExpression whereClause = user.deletedAt.isNull()
			.and(name != null ? user.name.contains(name) : null);

		List<User> users = queryFactory.select(user)
			.from(user)
			.where(whereClause)
			.orderBy(user.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		List<String> userIds = queryFactory.select(user.id)
			.from(user)
			.orderBy(user.id.desc())
			.fetch();

		return PaginatedListResponse.of(users, PageableResponse.of(pageable, userIds));
	}
}

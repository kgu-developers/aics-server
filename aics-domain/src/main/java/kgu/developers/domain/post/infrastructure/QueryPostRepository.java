package kgu.developers.domain.post.infrastructure;

import static kgu.developers.domain.comment.domain.QComment.comment;
import static kgu.developers.domain.post.domain.QPost.post;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kgu.developers.common.response.PageableResponse;
import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.post.domain.Category;
import kgu.developers.domain.post.domain.Post;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryPostRepository {
	private final JPAQueryFactory queryFactory;

	public PaginatedListResponse findAllByTitleContainingAndCategoryOrderByCreatedAtDesc(String keyword,
		Category category, Pageable pageable) {

		BooleanExpression whereClause = post.deletedAt.isNull()
			.and(keyword != null ? post.title.contains(keyword) : null)
			.and(category != null ? post.category.eq(category) : null);

		List<Post> posts = queryFactory.select(post)
			.from(post)
			.where(whereClause)
			.orderBy(post.isPinned.desc(), post.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		List<Long> postIds = queryFactory.select(post.id)
			.from(post)
			.where(whereClause)
			.orderBy(post.isPinned.desc(), post.createdAt.desc())
			.fetch();

		return PaginatedListResponse.of(posts, PageableResponse.of(pageable, postIds));
	}

	public void deleteAllByDeletedAtBefore(int retentionDays) {
		LocalDateTime thresholdDate = LocalDateTime.now().minusDays(retentionDays);
		queryFactory.delete(comment)
			.where(comment.post.deletedAt.isNotNull()
				.and(comment.post.deletedAt.before(thresholdDate)))
			.execute();

		queryFactory.delete(post)
			.where(post.deletedAt.isNotNull().and(post.deletedAt.before(thresholdDate)))
			.execute();
	}
}

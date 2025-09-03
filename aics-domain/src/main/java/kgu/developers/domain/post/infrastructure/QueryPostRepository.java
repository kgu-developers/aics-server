package kgu.developers.domain.post.infrastructure;

import static kgu.developers.domain.comment.domain.QComment.comment;
import static kgu.developers.domain.post.domain.QPost.post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.querydsl.jpa.JPAExpressions;
import kgu.developers.domain.post.domain.QPost;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
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

	public PaginatedListResponse findAllByTitleContainingAndCategoryOrderByCreatedAtDescIdDesc(List<String> keywords,
		Category category, Pageable pageable) {

		BooleanExpression whereClause = post.deletedAt.isNull()
			.and(category != null ? post.category.eq(category) : null);

		BooleanExpression keywordCondition = buildKeywordCondition(keywords, post.title);

		if (keywordCondition != null) {
			whereClause = whereClause.and(keywordCondition);
		}

		List<Post> posts = queryFactory.select(post)
			.from(post)
			.where(whereClause)
			.orderBy(post.isPinned.desc(), post.createdAt.desc(), post.id.desc())
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
			.where(comment.postId.in(
				JPAExpressions.select(post.id)
					.from(post)
					.where(post.deletedAt.isNotNull()
						.and(post.deletedAt.before(thresholdDate)))
			))
			.execute();

		queryFactory.delete(post)
			.where(post.deletedAt.isNotNull().and(post.deletedAt.before(thresholdDate)))
			.execute();
	}

	private BooleanExpression buildKeywordCondition(List<String> keywords, StringPath field) {
		if (keywords == null || keywords.isEmpty()) {
			return null;
		}

		BooleanExpression keywordCondition = null;

		for (String keyword : keywords) {
			if (keyword != null && !keyword.isBlank()) {
				BooleanExpression condition = field.contains(keyword);
				keywordCondition = (keywordCondition == null) ? condition : keywordCondition.or(condition);
			}
		}

		return keywordCondition;
	}

	public Optional<Post> findPreviousPost(Long id, LocalDateTime createdAt, Category category) {

		Post result = queryFactory
			.selectFrom(post)
			.where(
				post.createdAt.lt(createdAt)
					.or(post.createdAt.eq(createdAt).and(post.id.lt(id))),
				post.deletedAt.isNull(),
				post.category.eq(category)
			)
			.orderBy(post.createdAt.desc(), post.id.desc())
			.limit(1)
			.fetchOne();

		return Optional.ofNullable(result);
	}

	public Optional<Post> findNextPost(Long id, LocalDateTime createdAt, Category category) {
		QPost post = QPost.post;

		Post result = queryFactory
			.selectFrom(post)
			.where(
				post.createdAt.gt(createdAt)
					.or(post.createdAt.eq(createdAt).and(post.id.gt(id))),
				post.deletedAt.isNull(),
				post.category.eq(category)
			)
			.orderBy(post.createdAt.asc(), post.id.asc())
			.limit(1)
			.fetchOne();

		return Optional.ofNullable(result);
	}
}

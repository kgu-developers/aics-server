package kgu.developers.domain.post.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kgu.developers.common.response.PageableResponse;
import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static kgu.developers.domain.comment.QComment.comment;
import static kgu.developers.domain.post.domain.QPost.post;
import static kgu.developers.domain.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class QueryPostRepository {
	private final JPAQueryFactory queryFactory;

	public PaginatedListResponse findAllByTitleContainingOrderByCreatedAtDesc(String keyword,
																			  Pageable pageable) {
		if (keyword == null) keyword = "";
		List<Post> posts = queryFactory
			.select(post)
			.from(post)
			.where(post.title.contains(keyword))
			.orderBy(post.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		List<Long> postIds = queryFactory
			.select(post.id)
			.from(post)
			.where(post.title.contains(keyword))
			.orderBy(post.createdAt.desc())
			.fetch();

		return PaginatedListResponse.of(posts, PageableResponse.of(pageable, postIds));
	}

	public Optional<Post> findByIdWithAuthorAndComments(Long postId) {
		return Optional.ofNullable(
			queryFactory
				.select(post)
				.from(post)
				.leftJoin(post.author, user).fetchJoin()
				.leftJoin(post.comments, comment).fetchJoin()
				.where(post.id.eq(postId))
				.fetchOne()
		);
	}
}

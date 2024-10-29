package kgu.developers.domain.post.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kgu.developers.common.response.PageableResponse;
import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kgu.developers.domain.post.domain.QPost.post;

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
			.where(post.deletedAt.isNull())
			.orderBy(post.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		List<Long> postIds = queryFactory
			.select(post.id)
			.from(post)
			.where(post.title.contains(keyword))
			.where(post.deletedAt.isNull())
			.orderBy(post.createdAt.desc())
			.fetch();

		return PaginatedListResponse.of(posts, PageableResponse.of(pageable, postIds));
	}
}

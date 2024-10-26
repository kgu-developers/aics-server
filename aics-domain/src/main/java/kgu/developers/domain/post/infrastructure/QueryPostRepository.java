package kgu.developers.domain.post.infrastructure;

import static kgu.developers.domain.post.domain.QPost.post;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kgu.developers.common.response.PageableResponse;
import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.post.domain.Post;
import lombok.RequiredArgsConstructor;

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
}

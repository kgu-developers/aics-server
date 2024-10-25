package kgu.developers.core.domain.post.infrastructure;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kgu.developers.core.domain.post.Post;
import kgu.developers.core.domain.post.PostRepository;
import kgu.developers.core.domain.post.QPost;
import kgu.developers.core.domain.user.domain.QUser;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
	private final JpaPostRepository jpaPostRepository;
	private final JPAQueryFactory queryFactory;

	@Override
	public Post save(Post post) {
		return jpaPostRepository.save(post);
	}

	@Override
	public Page<Post> findPostsWithUserByKeyword(String keyword, Pageable pageable) {
		QPost post = QPost.post;
		QUser user = QUser.user;

		BooleanExpression condition = createKeywordCondition(keyword);

		List<Post> content = fetchPostsWithUserByCondition(post, user, condition, pageable);

		long total = fetchTotalCount(post, condition);

		return new PageImpl<>(content, pageable, total);
	}

	private BooleanExpression createKeywordCondition(String keyword) {
		return (keyword != null && !keyword.isEmpty())
			? QPost.post.title.containsIgnoreCase(keyword)
			: null;
	}

	private List<Post> fetchPostsWithUserByCondition(QPost post, QUser user, BooleanExpression condition,
		Pageable pageable) {
		return queryFactory
			.selectFrom(post)
			.join(post.author, user).fetchJoin()
			.where(condition)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(post.createdAt.desc())
			.fetch();
	}

	private long fetchTotalCount(QPost post, BooleanExpression condition) {
		return queryFactory
			.select(post.id)
			.where(condition)
			.fetch().size();
	}
}

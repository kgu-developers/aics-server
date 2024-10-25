package kgu.developers.core.domain.post.infrastructure;

import org.springframework.stereotype.Repository;

import kgu.developers.core.domain.post.Post;
import kgu.developers.core.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
	private final JpaPostRepository jpaPostRepository;

	@Override
	public Post save(Post post) {
		return jpaPostRepository.save(post);
	}
}

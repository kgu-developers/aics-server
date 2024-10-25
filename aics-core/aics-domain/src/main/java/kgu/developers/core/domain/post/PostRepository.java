package kgu.developers.core.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepository {
	Post save(Post post);

	Page<Post> findPostsWithUserByKeyword(String keyword, Pageable pageable);
}

package mock;

import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.post.domain.Category;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.post.domain.PostRepository;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class FakePostRepository implements PostRepository {
	@Override
	public Post save(Post post) {
		return null;
	}

	@Override
	public PaginatedListResponse<Post> findAllByTitleContainingAndCategoryOrderByCreatedAtDesc(String keyword, Category category, Pageable pageable) {
		return null;
	}

	@Override
	public Optional<Post> findById(Long postId) {
		return Optional.empty();
	}

	@Override
	public void deleteAllByDeletedAtBefore(int retentionDays) {

	}
}

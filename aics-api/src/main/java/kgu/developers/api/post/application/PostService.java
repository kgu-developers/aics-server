package kgu.developers.api.post.application;

import jakarta.transaction.Transactional;
import kgu.developers.api.post.presentation.exception.PostNotFoundException;
import kgu.developers.api.post.presentation.request.PostRequest;
import kgu.developers.api.post.presentation.response.PostDetailResponse;
import kgu.developers.api.post.presentation.response.PostPersistResponse;
import kgu.developers.api.post.presentation.response.PostSummaryPageResponse;
import kgu.developers.api.user.application.UserService;
import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.post.domain.PostRepository;
import kgu.developers.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;
	private final UserService userService;

	@Transactional
	public PostPersistResponse createPost(PostRequest request) {
		User author = userService.me();
		Post createPost = Post.create(request.title(), request.content(), author);
		postRepository.save(createPost);
		return PostPersistResponse.from(createPost.getId());
	}

	@Transactional
	public PostSummaryPageResponse getPostsByKeyword(PageRequest request, String keyword) {
		try {
			PaginatedListResponse<Post> paginatedListResponse = postRepository.findAllByTitleContainingOrderByCreatedAtDesc(
				keyword, request);
			return PostSummaryPageResponse.of(paginatedListResponse.contents(), paginatedListResponse.pageable());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Post getById(Long postId) {
		return postRepository.findById(postId)
			.filter(post -> post.getDeletedAt() == null)
			.orElseThrow(PostNotFoundException::new);
	}

	@Transactional
	public PostDetailResponse getPostById(Long postId) {
		Post post = getById(postId);
		post.increaseViews();
		return PostDetailResponse.from(post);
	}

	@Transactional
	public void updatePost(Long postId, PostRequest request) {
		Post updatePost = getById(postId);

		updatePost.updateTitle(request.title());
		updatePost.updateContent(request.content());
	}

	@Transactional
	public void deletePost(Long postId) {
		Post deletePost = getById(postId);
		deletePost.delete();
	}
}

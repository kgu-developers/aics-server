package kgu.developers.apis.api.post.application;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kgu.developers.apis.api.post.presentation.request.PostCreateRequest;
import kgu.developers.apis.api.post.presentation.response.PostPersistResponse;
import kgu.developers.apis.api.post.presentation.response.PostSummaryPageResponse;
import kgu.developers.apis.api.user.application.UserService;
import kgu.developers.core.common.response.PaginatedListResponse;
import kgu.developers.core.domain.post.domain.Post;
import kgu.developers.core.domain.post.domain.PostRepository;
import kgu.developers.core.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;
	private final UserService userService;

	@Transactional
	public PostPersistResponse createPost(PostCreateRequest request) {
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
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

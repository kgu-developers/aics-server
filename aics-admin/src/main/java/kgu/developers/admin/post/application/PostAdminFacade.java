package kgu.developers.admin.post.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.admin.post.presentation.request.PostRequest;
import kgu.developers.admin.post.presentation.response.PostPersistResponse;
import kgu.developers.domain.post.application.command.PostCommandService;
import kgu.developers.domain.post.application.command.PostSchedulingService;
import kgu.developers.domain.post.application.query.PostQueryService;
import kgu.developers.domain.post.domain.Post;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostAdminFacade {
	private final PostCommandService postCommandService;
	private final PostQueryService postQueryService;
	private final PostSchedulingService postSchedulingService;

	public PostPersistResponse createPost(PostRequest request) {
		Long id = postCommandService.createPost(request.title(), request.content(), request.category());
		return PostPersistResponse.from(id);
	}

	@Transactional
	public void updatePost(Long postId, PostRequest request) {
		Post post = postQueryService.getById(postId);
		postCommandService.updatePost(post, request.title(), request.content(), request.category());
	}

	@Transactional
	public void togglePostPinStatus(Long postId) {
		Post post = postQueryService.getById(postId);
		postCommandService.togglePostPinStatus(post);
	}

	@Transactional
	public void deletePost(Long postId) {
		Post post = postQueryService.getById(postId);
		postCommandService.deletePost(post);
	}

	public String getLastCleanupRunTime() {
		return postSchedulingService.getFormattedLastCleanupRunTime();
	}
}

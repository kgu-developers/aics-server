package kgu.developers.api.post.application;

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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;
	private final UserService userService;

	public static final int POST_RETENTION_DAYS = 60 * 60 * 24 * 30;

	private LocalDateTime lastScheduledRun;

	@Transactional
	public PostPersistResponse createPost(PostRequest request) {
		User author = userService.me();
		Post createPost = Post.create(request.title(), request.content(), author);
		postRepository.save(createPost);
		return PostPersistResponse.from(createPost.getId());
	}

	public PostSummaryPageResponse getPostsByKeyword(PageRequest request, String keyword) {
		PaginatedListResponse<Post> paginatedListResponse = postRepository.findAllByTitleContainingOrderByCreatedAtDesc(
			keyword, request);
		return PostSummaryPageResponse.of(paginatedListResponse.contents(), paginatedListResponse.pageable());
	}

	@Transactional(readOnly = true)
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
	public void togglePostPinStatus(Long postId) {
		Post post = getById(postId);
		post.togglePinned();
	}

	@Transactional
	public void deletePost(Long postId) {
		Post post = getById(postId);
		post.delete();
	}

	@Scheduled(cron = "0 0 0 * * *")
	@Transactional
	public void cleanupOldDeletedPosts() {
		postRepository.deleteAllByDeletedAtBefore(POST_RETENTION_DAYS);
		lastScheduledRun = LocalDateTime.now();
	}

	public String getFormattedLastCleanupRunTime() {
		if (lastScheduledRun == null) {
			return "아직 클린업 작업이 실행되지 않았습니다.";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
		return "최근 삭제된 게시글 정리 시간: " + lastScheduledRun.format(formatter);
	}

	public Post getById(Long postId) {
		return postRepository.findById(postId)
			.filter(post -> post.getDeletedAt() == null)
			.orElseThrow(PostNotFoundException::new);
	}
}

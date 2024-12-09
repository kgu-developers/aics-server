package kgu.developers.api.post.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.api.post.presentation.exception.PostNotFoundException;
import kgu.developers.api.post.presentation.request.PostRequest;
import kgu.developers.api.post.presentation.response.PostDetailResponse;
import kgu.developers.api.post.presentation.response.PostPersistResponse;
import kgu.developers.api.post.presentation.response.PostSummaryPageResponse;
import kgu.developers.api.post.presentation.response.PostTitleResponse;
import kgu.developers.api.user.application.UserService;
import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.post.domain.Category;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.post.domain.PostRepository;
import kgu.developers.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;
	private final UserService userService;

	public static final int POST_RETENTION_DAYS = 60 * 60 * 24 * 30;

	private LocalDateTime lastScheduledRun;

	@Transactional
	public PostPersistResponse createPost(PostRequest request, Category category) {
		User author = userService.me();
		Post createPost = Post.create(request.title(), request.content(), category, author);
		Long id = postRepository.save(createPost).getId();
		return PostPersistResponse.from(id);
	}

	public PostSummaryPageResponse getPostsByKeywordAndCategory(PageRequest request, String keyword,
		Category category) {
		PaginatedListResponse<Post> paginatedListResponse = postRepository.findAllByTitleContainingAndCategoryOrderByCreatedAtDesc(
			keyword, category, request);
		return PostSummaryPageResponse.of(paginatedListResponse.contents(), paginatedListResponse.pageable());
	}

	@Transactional
	public PostDetailResponse getPostByIdWithPrevAndNext(Long postId) {
		Post post = getById(postId);
		post.increaseViews();

		LocalDateTime timestamp = post.getCreatedAt();
		Category category = post.getCategory();

		Post prevPost = postRepository.findByPrevPost(timestamp, category).orElse(null);
		Post nextPost = postRepository.findByNextPost(timestamp, category).orElse(null);

		PostTitleResponse prevPostResponse = PostTitleResponse.from(prevPost);
		PostTitleResponse nextPostResponse = PostTitleResponse.from(nextPost);
		return PostDetailResponse.from(post, prevPostResponse, nextPostResponse);
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

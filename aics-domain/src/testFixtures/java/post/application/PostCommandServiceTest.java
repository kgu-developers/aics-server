package post.application;

import static kgu.developers.domain.post.domain.Category.NEWS;
import static kgu.developers.domain.post.domain.Category.NOTIFICATION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.post.application.command.PostCommandService;
import kgu.developers.domain.post.domain.Category;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.post.domain.PostRepository;
import kgu.developers.domain.user.domain.User;
import mock.TestContainer;

public class PostCommandServiceTest {
	private PostCommandService postCommandService;
	private PostRepository postRepository;

	@BeforeEach
	public void init() {
		TestContainer testContainer = new TestContainer();
		postRepository = testContainer.postRepository;
		postCommandService = testContainer.postCommandService;
		
		User author = testContainer.userQueryService.me();

		postRepository.save(Post.create(
			"테스트용 제목1", "테스트용 내용1",
			NEWS, author
		));

		postRepository.save(Post.create(
			"테스트용 제목2", "테스트용 내용2",
			NEWS, author
		));
	}

	@Test
	@DisplayName("createPost는 게시글을 생성할 수 있다")
	public void createPost_Success() {
		// given
		String title = "test";
		String content = "test";
		Category category = NOTIFICATION;

		// when
		Long response = postCommandService.createPost(title, content, category);

		// then
		assertEquals(3, response);

		// when
		Post created = postRepository.findById(3L).orElse(null);

		// then
		assertEquals(title, created.getTitle());
		assertEquals(content, created.getContent());
		assertEquals(category.getDescription(), created.getCategory().getDescription());
	}

	@Test
	@DisplayName("updatePost는 게시글의 내용을 수정할 수 있다")
	public void updatePost_Success() {
		// given
		Long postId = 1L; // 기존 데이터 중 하나를 수정
		Post post = postRepository.findById(postId).orElseThrow();

		String newTitle = "Updated Title";
		String newContent = "Updated Content";
		Category newCategory = NOTIFICATION;

		// when
		postCommandService.updatePost(post, newTitle, newContent, newCategory);

		// then
		assertEquals(newTitle, post.getTitle());
		assertEquals(newContent, post.getContent());
		assertEquals(newCategory, post.getCategory());
	}

	@Test
	@DisplayName("togglePostPinStatus는 게시글의 고정 상태를 토글할 수 있다")
	public void togglePostPinStatus_Success() {
		// given
		Long postId = 1L;
		Post post = postRepository.findById(postId).orElseThrow();

		boolean initialPinnedStatus = post.isPinned();

		// when
		postCommandService.togglePostPinStatus(post);

		// then
		assertEquals(!initialPinnedStatus, post.isPinned());

		// when
		postCommandService.togglePostPinStatus(post);

		// then
		assertEquals(initialPinnedStatus, post.isPinned());
	}

	@Test
	@DisplayName("increaseViews는 게시글의 조회수를 증가시킬 수 있다")
	public void increaseViews_Success() {
		// given
		Long postId = 1L;
		Post post = postRepository.findById(postId).orElseThrow();

		int initialViews = post.getViews();

		// when
		postCommandService.increaseViews(post);

		// then
		assertEquals(initialViews + 1, post.getViews());
	}

	@Test
	@DisplayName("deletePost는 게시글을 삭제할 수 있다")
	public void deletePost_Success() {
		// given
		Long postId = 1L;
		Post post = postRepository.findById(postId).orElseThrow();

		// when
		postCommandService.deletePost(post);

		// then
		assertNotNull(post.getDeletedAt());
	}
}

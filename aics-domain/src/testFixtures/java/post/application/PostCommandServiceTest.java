package post.application;

import static kgu.developers.domain.post.domain.Category.NOTIFICATION;
import static kgu.developers.domain.user.domain.Major.CSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import kgu.developers.domain.file.application.query.FileQueryService;
import mock.repository.FakeFileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import kgu.developers.domain.post.application.command.PostCommandService;
import kgu.developers.domain.post.domain.Category;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import mock.repository.FakePostRepository;
import mock.repository.FakeUserRepository;

public class PostCommandServiceTest {
	private PostCommandService postCommandService;

	@BeforeEach
	public void init() {
		FakeUserRepository fakeUserRepository = new FakeUserRepository();
		FakeFileRepository fakeFileRepository = new FakeFileRepository();
		UserQueryService userQueryService = new UserQueryService(fakeUserRepository);

		postCommandService = new PostCommandService(
			userQueryService,
			new FakePostRepository(),
			new FileQueryService(fakeFileRepository)
		);

		fakeUserRepository.save(User.builder()
			.id("202411345")
			.password("password1234")
			.name("홍길동")
			.email("test@kyonggi.ac.kr")
			.phone("010-1234-5678")
			.major(CSE)
			.build());

		UserDetails user = userQueryService.getUserById("202411345");
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(
			new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities())
		);
	}

	@Test
	@DisplayName("createPost는 게시글을 생성할 수 있다")
	public void createPost_Success() {
		// given
		String title = "test";
		String content = "test";
		Category category = NOTIFICATION;
		Long fileId = 1L;

		// when
		Long result = postCommandService.createPost(title, content, category, fileId);

		// then
		assertEquals(1L, result);
	}

	@Test
	@DisplayName("updatePost는 게시글의 내용을 수정할 수 있다")
	public void updatePost_Success() {
		// given
		Post post = Post.builder().build();

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
		Post post = Post.builder().build();

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
		Post post = Post.builder().build();

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
		Post post = Post.builder().build();
		assertNull(post.getDeletedAt(), "삭제 전에는 deletedAt이 null이어야 합니다");
		// when
		postCommandService.deletePost(post);

		// then
		assertNotNull(post.getDeletedAt());
	}
}

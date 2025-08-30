package post.application;

import static kgu.developers.domain.post.domain.Category.NOTIFICATION;
import static kgu.developers.domain.user.domain.Major.CSE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import kgu.developers.admin.post.application.PostAdminFacade;
import kgu.developers.admin.post.presentation.request.PostCreateRequest;
import kgu.developers.admin.post.presentation.request.PostUpdateRequest;
import kgu.developers.admin.post.presentation.response.PostPersistResponse;
import kgu.developers.domain.file.application.query.FileQueryService;
import kgu.developers.domain.post.application.command.PostCommandService;
import kgu.developers.domain.post.application.command.PostSchedulingService;
import kgu.developers.domain.post.application.query.PostQueryService;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.post.exception.PostNotFoundException;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import mock.repository.FakeFileRepository;
import mock.repository.FakePostRepository;
import mock.repository.FakeUserRepository;

public class PostAdminFacadeTest {
	private PostAdminFacade postAdminFacade;
	private FakePostRepository fakePostRepository;

	@BeforeEach
	public void init() {
		fakePostRepository = new FakePostRepository();
		FakeUserRepository fakeUserRepository = new FakeUserRepository();
		FakeFileRepository fakeFileRepository = new FakeFileRepository();
		UserQueryService userQueryService = new UserQueryService(fakeUserRepository);
		FileQueryService fileQueryService = new FileQueryService(fakeFileRepository);
		this.postAdminFacade = new PostAdminFacade(
			new PostCommandService(userQueryService, fakePostRepository, fileQueryService),
			new PostQueryService(fakePostRepository, fakeFileRepository, fakeUserRepository),
			new PostSchedulingService(fakePostRepository)
		);

		User author = fakeUserRepository.save(User.builder()
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

		fakePostRepository.save(
			Post.create(
				"post title", "post content", NOTIFICATION, author.getId(), null, false
			)
		);
	}

	@Test
	@DisplayName("createPost는 Post를 생성한다")
	void createPost_Success() {
		// given
		PostCreateRequest postCreateRequest = PostCreateRequest.builder()
			.title("new title")
			.content("new content")
			.category(NOTIFICATION)
			.build();
		Long fileId = 1L;

		// when
		PostPersistResponse post = postAdminFacade.createPost(fileId, postCreateRequest);
		Post found = fakePostRepository.findByIdAndDeletedAtIsNull(post.postId()).get();

		// then
		assertEquals("new title", found.getTitle());
	}

	@Test
	@DisplayName("updatePost는 Post를 수정한다")
	void updatePost_Success() {
		// given
		PostUpdateRequest request = PostUpdateRequest.builder()
			.title("new title")
			.content("new content")
			.category(NOTIFICATION)
			.build();

		// when
		postAdminFacade.updatePost(1L, request);
		Post found = fakePostRepository.findByIdAndDeletedAtIsNull(1L).get();

		// then
		assertEquals("new title", found.getTitle());
		assertEquals("new content", found.getContent());
	}

	@Test
	@DisplayName("updatePost는 존재하지 않는 Post를 수정하면 PostNotFoundException을 발생시킨다")
	void updatePost_throws_PostNotFoundException() {
		// given
		PostUpdateRequest request = PostUpdateRequest.builder()
			.title("new title")
			.content("new content")
			.category(NOTIFICATION)
			.build();

		// when
		// then
		assertThatThrownBy(() -> postAdminFacade.updatePost(2L, request))
			.isInstanceOf(PostNotFoundException.class);
	}

	@Test
	@DisplayName("togglePostPinStatus는 Post의 상태를 변경한다")
	void togglePostPinStatus_Success() {
		// given
		Post original = fakePostRepository.findByIdAndDeletedAtIsNull(1L).get();
		boolean originalBool = original.isPinned();

		// when
		postAdminFacade.togglePostPinStatus(1L);
		Post found = fakePostRepository.findByIdAndDeletedAtIsNull(1L).get();

		// then
		assertNotEquals(originalBool, found.isPinned());
	}

	@Test
	@DisplayName("deletePost는 Post를 삭제한다")
	void deletePost_Success() {
		// when
		postAdminFacade.deletePost(1L);

		// then
		Post result = fakePostRepository.findByIdAndDeletedAtIsNull(1L).orElse(null);
		assertNull(result);
	}

	@Test
	@DisplayName("deletePost는 존재하지 않는 Post를 수정하면 PostNotFoundException을 발생시킨다")
	void deletePost_Failed() {
		// when
		// then
		assertThatThrownBy(() -> postAdminFacade.deletePost(2L))
			.isInstanceOf(PostNotFoundException.class);
	}

	@Test
	@DisplayName("getLastCleanupRunTime는 마지막 scheduling cleaning 시간을 조회한다")
	void getLastCleanupRunTime_Success() {
		// when
		String lastCleanupRunTime = postAdminFacade.getLastCleanupRunTime();

		// then
		assertEquals("아직 클린업 작업이 실행되지 않았습니다.", lastCleanupRunTime);
	}
}

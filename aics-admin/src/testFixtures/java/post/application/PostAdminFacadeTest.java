package post.application;

import kgu.developers.admin.post.application.PostAdminFacade;
import kgu.developers.admin.post.presentation.request.PostRequest;
import kgu.developers.admin.post.presentation.response.PostPersistResponse;
import kgu.developers.domain.post.application.command.PostCommandService;
import kgu.developers.domain.post.application.command.PostSchedulingService;
import kgu.developers.domain.post.application.query.PostQueryService;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.post.domain.PostRepository;
import kgu.developers.domain.post.exception.PostNotFoundException;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.domain.UserRepository;
import mock.TestContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static kgu.developers.domain.post.domain.Category.NOTIFICATION;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class PostAdminFacadeTest {
	private PostAdminFacade postAdminFacade;
	private PostRepository fakePostRepository;

	@BeforeEach
	public void init() {
		TestContainer container = new TestContainer();
		this.fakePostRepository = container.postRepository;
		PostCommandService postCommandService = container.postCommandService;
		PostQueryService postQueryService = container.postQueryService;
		PostSchedulingService postSchedulingService = new PostSchedulingService(fakePostRepository);

		this.postAdminFacade = new PostAdminFacade(postCommandService, postQueryService, postSchedulingService);

		UserRepository userRepository = container.userRepository;
		User user = userRepository.findById("202411345").get();

		fakePostRepository.save(
			Post.create(
				"post title", "post content", NOTIFICATION, user
			)
		);
	}

	@Test
	@DisplayName("createPost는 Post를 생성할 수 있다.")
	void createPost_Success() {
		// given
		PostRequest postRequest = PostRequest.builder()
			.title("new title")
			.content("new content")
			.category(NOTIFICATION)
			.build();

		// when
		PostPersistResponse post = postAdminFacade.createPost(postRequest);
		Post found = fakePostRepository.findById(post.postId()).get();

		// then
		assertEquals("new title", found.getTitle());
	}


	@Test
	@DisplayName("updatePost는 Post를 수정할 수 있다.")
	void updatePost_Success() {
		// given
		PostRequest postRequest = PostRequest.builder()
			.title("new title")
			.content("new content")
			.category(NOTIFICATION)
			.build();

		// when
		postAdminFacade.updatePost(1L, postRequest);
		Post found = fakePostRepository.findById(1L).get();

		// then
		assertEquals("new title", found.getTitle());
		assertEquals("new content", found.getContent());
	}


	@Test
	@DisplayName("updatePost는 존재하지 않는 Post를 수정하면 PostNotFoundException을 발생시킨다.")
	void updatePost_throws_PostNotFoundException() {
		// given
		PostRequest postRequest = PostRequest.builder()
			.title("new title")
			.content("new content")
			.category(NOTIFICATION)
			.build();

		// when
		// then
		assertThatThrownBy(() -> postAdminFacade.updatePost(2L, postRequest))
			.isInstanceOf(PostNotFoundException.class);
	}


	@Test
	@DisplayName("togglePostPinStatus는 Post의 상태를 변경할 수 있다.")
	void togglePostPinStatus_Success() {
		// given
		Post original = fakePostRepository.findById(1L).get();
		boolean originalBool = original.isPinned();

		// when
		postAdminFacade.togglePostPinStatus(1L);
		Post found = fakePostRepository.findById(1L).get();

		// then
		assertNotEquals(originalBool, found.isPinned());
	}

	@Test
	@DisplayName("deletePost는 Post를 삭제할 수 있다.")
	void deletePost_Success() {
		// when
		postAdminFacade.deletePost(1L);

		// then
		Post found = fakePostRepository.findById(1L).get();
		assertNotNull(found.getDeletedAt());
	}

	@Test
	@DisplayName("deletePost는 존재하지 않는 Post를 수정하면 PostNotFoundException을 발생시킨다.")
	void deletePost_() {
		// when
		// then
		assertThatThrownBy(() -> postAdminFacade.deletePost(2L))
			.isInstanceOf(PostNotFoundException.class);
	}

	@Test
	@DisplayName("getLastCleanupRunTime는 마지막 scheduling cleaning 시간을 조회할 수 있다.")
	void getLastCleanupRunTime_Success() {
		// when
		String lastCleanupRunTime = postAdminFacade.getLastCleanupRunTime();

		// then
		assertEquals("아직 클린업 작업이 실행되지 않았습니다.", lastCleanupRunTime);
	}
}

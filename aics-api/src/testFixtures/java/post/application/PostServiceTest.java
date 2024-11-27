package post.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import kgu.developers.api.post.application.PostService;
import kgu.developers.api.post.presentation.exception.PostNotFoundException;
import kgu.developers.api.post.presentation.request.PostRequest;
import kgu.developers.api.post.presentation.response.PostDetailResponse;
import kgu.developers.api.post.presentation.response.PostPersistResponse;
import kgu.developers.api.post.presentation.response.PostSummaryPageResponse;
import kgu.developers.domain.post.domain.Category;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.user.domain.Major;
import kgu.developers.domain.user.domain.User;
import mock.FakePostRepository;
import mock.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PostServiceTest {
	private PostService postService;

	@BeforeEach
	public void init() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		FakeUserRepository fakeUserRepository = new FakeUserRepository();
		FakePostRepository fakePostRepository = new FakePostRepository();

		FakeUserService userService = new FakeUserService(fakeUserRepository, bCryptPasswordEncoder);

		this.postService = new PostService(fakePostRepository, userService);

		User user1 = User.create(
			"202411345",
			bCryptPasswordEncoder.encode("password1234"),
			"홍길동",
			"test@kyonggi.ac.kr",
			"010-1234-5678",
			Major.CSE);

		User user2 = User.create(
			"202411346",
			bCryptPasswordEncoder.encode("password5678"),
			"신짱구",
			"shin@kyonggi.ac.kr",
			"010-5678-1234",
			Major.AIT);

		fakeUserRepository.save(user1);
		fakeUserRepository.save(user2);

		fakePostRepository.save(Post.create(
			"테스트용 제목1", "테스트용 내용1",
			Category.DEPT_INFO, user1
		));

		fakePostRepository.save(Post.create(
			"테스트용 제목2", "테스트용 내용2",
			Category.DEPT_INFO, user1
		));
	}

	@Test
	@DisplayName("createPost는 게시글을 생성할 수 있다")
	public void createPost_Success() {
		// given
		PostRequest request = PostRequest.builder()
			.title("테스트용 제목3")
			.content("테스트용 내용3")
			.build();
		Category category = Category.DEPT_INFO;

		// when
		PostPersistResponse response = postService.createPost(request, category);

		// then
		assertEquals(3, response.postId());
	}

	@Test
	@DisplayName("getPostById는 게시글을 조회할 수 있다")
	public void getPostById_Success() {
		// given
		Long postId = 1L;

		// when
		PostDetailResponse response = postService.getPostById(postId);

		// then
		assertEquals(postId, response.postId());
	}

	@Test
	@DisplayName("getPostById는 존재하지 않는 ID로 조회시 PostNotFoundException을 발생시킨다")
	public void getPostById_Throws_PostNotFoundException() {
		// given
		Long postId = 10L;

		// when
		// then
		assertThatThrownBy(
			() -> postService.getPostById(postId)
		).isInstanceOf(PostNotFoundException.class);
	}

	@Test
	@DisplayName("getPostsByKeywordAndCategory는 게시글을 페이징 조회할 수 있다")
	public void getPostsByKeywordAndCategory_Success() {
		// given
		String keyword = "제목";
		Category category = Category.DEPT_INFO;
		int page = 0;
		int size = 10;

		// when
		PostSummaryPageResponse posts = postService.getPostsByKeywordAndCategory(
			PageRequest.of(page, size), keyword, category
		);

		// then
		assertEquals(2, posts.contents().size());
	}

	@Test
	@DisplayName("togglePostPinStatus는 게시글의 고정 상태를 변경할 수 있다")
	public void togglePostPinStatus_Success() {
		// given
		Long postId = 1L;
		PostDetailResponse before = postService.getPostById(postId);

		// when
		postService.togglePostPinStatus(postId);

		// then
		PostDetailResponse after = postService.getPostById(postId);
		assertNotEquals(before.isPinned(), after.isPinned());
	}

	@Test
	@DisplayName("deletePost 이후 게시글을 조회할 수 없다")
	public void deletePost_Throws_PostNotFoundException() {
		// given
		Long postId = 1L;

		// when
		postService.deletePost(postId);

		// then
		assertThatThrownBy(
			() -> postService.getPostById(postId)
		).isInstanceOf(PostNotFoundException.class);
	}
}

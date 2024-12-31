package post.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import kgu.developers.api.post.presentation.request.PostRequest;
import kgu.developers.api.post.presentation.response.PostPersistResponse;
/*
public class PostServiceTest {
	private PostService postService;

	@BeforeEach
	public void init() {
		FakePostRepository fakePostRepository = new FakePostRepository();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		FakeUserRepository fakeUserRepository = new FakeUserRepository();
		UserFacade userFacade = new UserFacade(bCryptPasswordEncoder, fakeUserRepository);

		this.postService = new PostService(fakePostRepository, userFacade);

		fakeUserRepository.save(User.builder()
			.id("202411345")
			.password(bCryptPasswordEncoder.encode("password1234"))
			.name("홍길동")
			.email("test@kyonggi.ac.kr")
			.phone("010-1234-5678")
			.major(Major.CSE)
			.build());

		UserDetails user = userFacade.getUserById("202411345");
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(
			new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities())
		);

		fakePostRepository.save(Post.create(
			"테스트용 제목1", "테스트용 내용1",
			Category.DEPT_INFO, userFacade.getUserById("202411345")
		));

		fakePostRepository.save(Post.create(
			"테스트용 제목2", "테스트용 내용2",
			Category.DEPT_INFO, userFacade.getUserById("202411345")
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

		// when
		Post created = postService.getById(response.postId());

		// then
		assertEquals(request.title(), created.getTitle());
		assertEquals(request.content(), created.getContent());
		assertEquals(category.getDescription(), created.getCategory().getDescription());
	}

	@Test
	@DisplayName("getPostById는 해당 게시글과 이전, 다음 게시글을 조회할 수 있다")
	public void getPostById_Success() {
		// given
		Long postId = 1L;

		// when
		PostDetailResponse response = postService.getPostByIdWithPrevAndNext(postId);

		// then
		assertEquals(postId, response.postId());
		assertNull(response.prevPost());
		assertEquals(response.nextPost().postId(), 2L);
		assertEquals(response.nextPost().title(), "테스트용 제목2");
	}

	@Test
	@DisplayName("getPostById는 마지막 게시글 조회 시 다음 게시글은 null이어야 한다")
	public void getPostById_LastPost_Success() {
		// given
		Long lastPostId = 2L;

		// when
		PostDetailResponse response = postService.getPostByIdWithPrevAndNext(lastPostId);

		// then
		assertEquals(lastPostId, response.postId());
		assertNull(response.nextPost());
		assertEquals(response.prevPost().postId(), 1L);
		assertEquals(response.prevPost().title(), "테스트용 제목1");
	}

	@Test
	@DisplayName("getPostById는 존재하지 않는 ID로 조회시 PostNotFoundException을 발생시킨다")
	public void getPostById_Throws_PostNotFoundException() {
		// given
		Long postId = 10L;

		// when
		// then
		assertThatThrownBy(
			() -> postService.getPostByIdWithPrevAndNext(postId)
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
		PostDetailResponse before = postService.getPostByIdWithPrevAndNext(postId);

		// when
		postService.togglePostPinStatus(postId);

		// then
		PostDetailResponse after = postService.getPostByIdWithPrevAndNext(postId);
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
			() -> postService.getPostByIdWithPrevAndNext(postId)
		).isInstanceOf(PostNotFoundException.class);
	}
}
*/
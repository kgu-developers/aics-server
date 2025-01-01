package comment.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
public class CommentServiceTest {
	private CommentService commentService;

	@BeforeEach
	public void init() {
		FakeCommentRepository fakeCommentRepository = new FakeCommentRepository();
		TestContainer testContainer = new TestContainer();

		this.commentService = new CommentService(fakeCommentRepository, testContainer.postService,
			testContainer.userFacade);

		testContainer.userRepository.save(User.builder()
			.id("202411345")
			.password("password1234")
			.name("홍길동")
			.email("test@kyonggi.ac.kr")
			.phone("010-1234-5678")
			.major(Major.CSE)
			.build());

		User author = testContainer.userFacade.getUserById("202411345");

		Post post = testContainer.postRepository.save(Post.create(
			"테스트용 제목1", "테스트용 내용1", Category.DEPT_INFO, author
		));

		Comment delete = fakeCommentRepository.save(Comment.builder()
			.author(testContainer.userFacade.getUserById("202411345"))
			.content("deleted")
			.post(post)
			.build()
		);
		delete.delete();

		fakeCommentRepository.save(Comment.builder()
			.author(testContainer.userFacade.getUserById("202411345"))
			.content("get")
			.post(post)
			.build()
		);

		fakeCommentRepository.save(Comment.builder()
			.author(testContainer.userFacade.getUserById("202411345"))
			.content("test!")
			.post(post)
			.build()
		);

		UserDetails user = testContainer.userFacade.getUserById("202411345");
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(
			new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities())
		);
	}

	@Test
	@DisplayName("createComment는 댓글을 생성할 수 있다.")
	public void createComment_Success() {
		// given
		CommentRequest commentRequest = CommentRequest.builder()
			.content("test")
			.postId(1L)
			.build();

		// when
		CommentPersistResponse response = commentService.createComment(commentRequest);

		// then
		assertEquals(response.commentId(), 4L);
		assertEquals(commentService.getById(4L).getContent(), "test");
	}

	@Test
	@DisplayName("getComments는 등록된 순서 오름차순으로 정렬하고, 삭제된 댓글을 제외하여 조회한다.")
	public void getComments_Success() {
		// given
		Long postId = 1L;

		// when
		CommentListResponse response = commentService.getComments(postId);
		List<CommentResponse> comments = response.contents();

		// then
		assertEquals(comments.size(), 2);
		assertEquals(comments.get(0).content(), "get");
		assertEquals(comments.get(1).content(), "test!");
	}

	@Test
	@DisplayName("updateComment는 댓글의 내용을 수정할 수 있다.")
	public void updateComment_Success() {
		// given
		Long commentId = 2L;
		CommentUpdateRequest request = CommentUpdateRequest.builder()
			.content("update")
			.build();

		// when
		commentService.updateComment(commentId, request);

		// then
		assertEquals(commentService.getById(commentId).getContent(), "update");
	}

	@Test
	@DisplayName("deleteComment는 댓글을 삭제할 수 있다.")
	public void deleteComment_Success() {
		// given
		Long commentId = 2L;

		// when
		commentService.deleteComment(commentId);

		// then
		assertThatThrownBy(() -> commentService.getById(commentId))
			.isInstanceOf(CommentNotFoundException.class)
			.hasMessage("해당 댓글을 찾을 수 없습니다.");
	}

	@Test
	@DisplayName("getById는 해당 댓글을 가져올 수 있다.")
	public void getById_Success() {
		// given
		Long commentId = 2L;

		// when
		Comment response = commentService.getById(commentId);

		// then
		assertEquals(response.getId(), commentId);
		assertEquals(response.getContent(), "get");
	}

	@Test
	@DisplayName("getById는 존재하지 않는 댓글을 조회할 경우 CommentNotFoundException을 발생시킨다.")
	public void getById_NotFound_ThrowsException() {
		// given
		Long commentId = 0L;

		// when
		// then
		assertThatThrownBy(() -> commentService.getById(commentId))
			.isInstanceOf(CommentNotFoundException.class)
			.hasMessage("해당 댓글을 찾을 수 없습니다.");
	}
}
*/
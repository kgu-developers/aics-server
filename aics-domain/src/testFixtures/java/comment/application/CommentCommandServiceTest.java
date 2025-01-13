package comment.application;

import static kgu.developers.domain.post.domain.Category.NEWS;
import static kgu.developers.domain.user.domain.Major.CSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import kgu.developers.domain.comment.application.command.CommentCommandService;
import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.user.domain.User;
import mock.FakeCommentRepository;
import mock.TestContainer;

public class CommentCommandServiceTest {
	private CommentCommandService commentCommandService;
	private FakeCommentRepository fakeCommentRepository;

	@BeforeEach
	public void init() {
		TestContainer testContainer = new TestContainer();
		fakeCommentRepository = new FakeCommentRepository();

		this.commentCommandService = new CommentCommandService(testContainer.postQueryService,
			testContainer.userQueryService, fakeCommentRepository);

		testContainer.userRepository.save(User.builder()
			.id("202411345")
			.password("password1234")
			.name("홍길동")
			.email("test@kyonggi.ac.kr")
			.phone("010-1234-5678")
			.major(CSE)
			.build());

		User author = testContainer.userQueryService.getUserById("202411345");

		Post post = testContainer.postRepository.save(Post.create(
			"테스트용 제목1", "테스트용 내용1", NEWS, author
		));

		fakeCommentRepository.save(Comment.builder()
			.author(testContainer.userQueryService.getUserById("202411345"))
			.content("get")
			.post(post)
			.build()
		);

		UserDetails user = testContainer.userQueryService.getUserById("202411345");
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(
			new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities())
		);
	}

	@Test
	@DisplayName("createComment는 댓글을 생성할 수 있다.")
	public void createComment_Success() {
		// given
		String content = "content";
		Long postId = 1L;

		// when
		Long commentId = commentCommandService.createComment(content, postId);

		// then
		Comment comment = fakeCommentRepository.findById(commentId).orElse(null);
		assertEquals(comment.getId(), 2L);
		assertEquals(comment.getContent(), "content");
	}

	@Test
	@DisplayName("updateComment는 댓글을 수정할 수 있다.")
	public void updateComment_Success() {
		// given
		Comment comment = fakeCommentRepository.findById(1L).orElse(null);
		String content = "content";

		// when
		commentCommandService.updateComment(comment, content);

		// then
		assertEquals(comment.getContent(), "content");
	}

	@Test
	@DisplayName("deleteComment는 댓글을 삭제할 수 있다.")
	public void deleteComment_Success() {
		// given
		Comment comment = fakeCommentRepository.findById(1L).orElse(null);

		// when
		commentCommandService.deleteComment(comment);

		// then
		assertNotEquals(comment.getDeletedAt(), null);
	}
}

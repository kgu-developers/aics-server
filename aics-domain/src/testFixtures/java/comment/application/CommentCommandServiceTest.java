package comment.application;

import static kgu.developers.domain.post.domain.Category.NEWS;
import static kgu.developers.domain.user.domain.Major.CSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import kgu.developers.domain.comment.application.command.CommentCommandService;
import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.post.application.query.PostQueryService;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import mock.FakeCommentRepository;
import mock.FakePostRepository;
import mock.FakeUserRepository;

public class CommentCommandServiceTest {
	private CommentCommandService commentCommandService;

	@BeforeEach
	public void init() {
		FakeCommentRepository fakeCommentRepository = new FakeCommentRepository();

		FakePostRepository fakePostRepository = new FakePostRepository();
		PostQueryService postQueryService = new PostQueryService(fakePostRepository);

		FakeUserRepository fakeUserRepository = new FakeUserRepository();
		UserQueryService userQueryService = new UserQueryService(fakeUserRepository);

		commentCommandService = new CommentCommandService(postQueryService, userQueryService, fakeCommentRepository);

		fakeUserRepository.save(User.builder()
			.id("202411345")
			.password("password1234")
			.name("홍길동")
			.email("test@kyonggi.ac.kr")
			.phone("010-1234-5678")
			.major(CSE)
			.build());

		User author = User.builder()
			.build();

		fakePostRepository.save(Post.create(
			"테스트용 제목1", "테스트용 내용1", NEWS, author
		));

		UserDetails user = userQueryService.getUserById("202411345");
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
		Long response = commentCommandService.createComment(content, postId);

		// then
		assertEquals(response, 1L);
	}

	@Test
	@DisplayName("updateComment는 댓글을 수정할 수 있다.")
	public void updateComment_Success() {
		// given
		User user = User.builder().build();
		Post post = Post.builder().build();
		Comment comment = Comment.create("test", user, post);

		String newContent = "content";

		// when
		commentCommandService.updateComment(comment, newContent);

		// then
		assertEquals(comment.getContent(), "content");
	}

	@Test
	@DisplayName("deleteComment는 댓글을 삭제할 수 있다.")
	public void deleteComment_Success() {
		// given
		User user = User.builder().build();
		Post post = Post.builder().build();
		Comment comment = Comment.create("test", user, post);

		// when
		commentCommandService.deleteComment(comment);

		// then
		assertNotNull(comment.getDeletedAt());
	}
}

package comment.application;

import static kgu.developers.domain.post.domain.Category.NEWS;
import static kgu.developers.domain.user.domain.Major.CSE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import kgu.developers.domain.comment.application.query.CommentQueryService;
import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.comment.exception.CommentNotFoundException;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.user.domain.User;
import mock.FakeCommentRepository;
import mock.TestContainer;

public class CommentQueryServiceTest {
	private CommentQueryService commentQueryService;

	@BeforeEach
	public void init() {
		TestContainer testContainer = new TestContainer();
		FakeCommentRepository fakeCommentRepository = new FakeCommentRepository();

		this.commentQueryService = new CommentQueryService(fakeCommentRepository);

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

		Comment delete = fakeCommentRepository.save(Comment.builder()
			.author(testContainer.userQueryService.getUserById("202411345"))
			.content("deleted")
			.post(post)
			.build()
		);
		delete.delete();

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
	@DisplayName("getComments는 등록된 순서 오름차순으로 정렬하고, 삭제된 댓글을 제외하여 조회한다.")
	public void getComments_Success() {
		// given
		Long postId = 1L;

		// when
		List<Comment> comments = commentQueryService.getComments(postId);

		// then
		assertEquals(comments.size(), 1);
		assertEquals(comments.get(0).getContent(), "get");
	}

	@Test
	@DisplayName("getById는 해당 댓글을 가져올 수 있다.")
	public void getById_Success() {
		// given
		Long commentId = 2L;

		// when
		Comment response = commentQueryService.getById(commentId);

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
		assertThatThrownBy(() -> commentQueryService.getById(commentId))
			.isInstanceOf(CommentNotFoundException.class)
			.hasMessage("해당 댓글을 찾을 수 없습니다.");
	}
}

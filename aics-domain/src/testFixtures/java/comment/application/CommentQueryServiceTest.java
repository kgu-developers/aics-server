package comment.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.comment.application.query.CommentQueryService;
import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.comment.exception.CommentNotFoundException;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.user.domain.User;
import mock.FakeCommentRepository;

public class CommentQueryServiceTest {
	private CommentQueryService commentQueryService;

	@BeforeEach
	public void init() {
		FakeCommentRepository fakeCommentRepository = new FakeCommentRepository();
		commentQueryService = new CommentQueryService(fakeCommentRepository);

		User user = User.builder()
			.build();
		
		Post post = Post.builder()
			.id(1L)
			.build();

		Comment delete = fakeCommentRepository.save(Comment.builder()
			.author(user)
			.content("deleted")
			.post(post)
			.build()
		);

		fakeCommentRepository.save(Comment.builder()
			.author(user)
			.content("get")
			.post(post)
			.build()
		);
		delete.delete();
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

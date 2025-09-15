package comment.application;

import static kgu.developers.domain.post.domain.Category.NEWS;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.comment.application.query.CommentQueryService;
import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.comment.exception.CommentNotFoundException;
import kgu.developers.domain.post.domain.Post;
import mock.repository.FakeCommentRepository;
import mock.repository.FakePostRepository;

public class CommentQueryServiceTest {
	private CommentQueryService commentQueryService;

	private static final int TARGET_COMMENT_COUNT = 1;
	private static final String TARGET_COMMENT_CONTENT = "SW 부트캠프 모집이 정말 기대됩니다.";
	private static final Long SAVED_COMMENT_ID = 2L;
	private static final Long NOT_EXIST_COMMENT_ID = 3L;
	private static final Long TEST_POST_ID = 1L;
	private static final String TEST_AUTHOR_ID = "202312345";

	@BeforeEach
	public void init() {
		FakeCommentRepository fakeCommentRepository = new FakeCommentRepository();
		commentQueryService = new CommentQueryService(fakeCommentRepository);
		Post post = saveTestPost();
		deletedComment(fakeCommentRepository, post.getId());
		saveTestComment(fakeCommentRepository, post.getId());
	}

	private static void deletedComment(FakeCommentRepository fakeCommentRepository, Long postId) {
		Comment commentToDelete = fakeCommentRepository.save(
			Comment.create("삭제된 댓글 입니다", TEST_AUTHOR_ID, postId)
		);
		commentToDelete.delete();
		fakeCommentRepository.save(commentToDelete);
	}

	private static void saveTestComment(FakeCommentRepository fakeCommentRepository, Long postId) {
		fakeCommentRepository.save(
			Comment.create(TARGET_COMMENT_CONTENT, TEST_AUTHOR_ID, postId)
		);
	}

	private static Post saveTestPost() {
		FakePostRepository fakePostRepository = new FakePostRepository();
		Post commentedPost = Post.create("SW 부트캠프 4기 교육생 모집",
			"SW전문인재양성사업단에서는 SW부트캠프 4기 교육생을 모집합니다.", NEWS, TEST_AUTHOR_ID, null, false);
		return fakePostRepository.save(commentedPost);
	}

	@Test
	@DisplayName("getComments는 등록된 순서 오름차순으로 정렬하고, 삭제된 댓글을 제외하여 조회한다.")
	public void getComments_Success() {
		// given
		// when
		List<Comment> comments = commentQueryService.getComments(TEST_POST_ID);

		// then
		assertEquals(TARGET_COMMENT_COUNT, comments.size());
		assertTrue(comments.stream().findFirst().isPresent(), "댓글이 존재하지 않습니다.");
		assertEquals(TARGET_COMMENT_CONTENT, comments.stream().findFirst().get().getContent());
	}

	@Test
	@DisplayName("getById는 해당 댓글을 가져올 수 있다.")
	public void getById_Success() {
		// given
		Long savedCommentId = SAVED_COMMENT_ID;

		// when
		Comment result = commentQueryService.getById(savedCommentId);

		// then
		assertNotNull(result);
		assertNull(result.getDeletedAt());
		assertEquals(savedCommentId, result.getId());
		assertEquals(TARGET_COMMENT_CONTENT, result.getContent());
	}

	@Test
	@DisplayName("getById는 존재하지 않는 댓글을 조회할 경우 CommentNotFoundException을 발생시킨다.")
	public void getById_NotFound_ThrowsException() {
		// given
		// when
		// then
		assertThatThrownBy(() -> commentQueryService.getById(NOT_EXIST_COMMENT_ID))
			.isInstanceOf(CommentNotFoundException.class)
			.hasMessage("해당 댓글을 찾을 수 없습니다.");
	}
}

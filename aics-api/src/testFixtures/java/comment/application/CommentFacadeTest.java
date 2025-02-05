package comment.application;

import static kgu.developers.domain.post.domain.Category.NEWS;
import static kgu.developers.domain.user.domain.Major.CSE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import kgu.developers.api.comment.application.CommentFacade;
import kgu.developers.api.comment.presentation.request.CommentRequest;
import kgu.developers.api.comment.presentation.request.CommentUpdateRequest;
import kgu.developers.api.comment.presentation.response.CommentListResponse;
import kgu.developers.api.comment.presentation.response.CommentPersistResponse;
import kgu.developers.api.comment.presentation.response.CommentResponse;
import kgu.developers.domain.comment.application.command.CommentCommandService;
import kgu.developers.domain.comment.application.command.CommentSchedulingService;
import kgu.developers.domain.comment.application.query.CommentQueryService;
import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.post.application.query.PostQueryService;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import mock.repository.FakeCommentRepository;
import mock.repository.FakePostRepository;
import mock.repository.FakeUserRepository;

public class CommentFacadeTest {
	private CommentFacade commentFacade;

	@BeforeEach
	public void init() {
		FakeCommentRepository fakeCommentRepository = new FakeCommentRepository();
		FakeUserRepository fakeUserRepository = new FakeUserRepository();
		FakePostRepository fakePostRepository = new FakePostRepository();

		UserQueryService userQueryService = new UserQueryService(fakeUserRepository);
		commentFacade = new CommentFacade(
			new CommentCommandService(
				new PostQueryService(fakePostRepository),
				userQueryService,
				fakeCommentRepository
			),
			new CommentQueryService(fakeCommentRepository),
			new CommentSchedulingService(fakeCommentRepository)
		);

		User author = fakeUserRepository.save(User.builder()
			.id("202411345")
			.password("password1234")
			.name("홍길동")
			.email("test@kyonggi.ac.kr")
			.phone("010-1234-5678")
			.major(CSE)
			.build());

		Post post = fakePostRepository.save(Post.create(
			"테스트용 제목1", "테스트용 내용1", NEWS, author, null
		));

		fakeCommentRepository.save(Comment.create(
			"test1", author, post
		));

		Comment delete = fakeCommentRepository.save(Comment.create(
			"test2", author, post
		));
		delete.delete();

		UserDetails user = userQueryService.getUserById("202411345");
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(
			new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities())
		);
	}

	@Test
	@DisplayName("createComment는 댓글을 생성한다")
	public void createComment_Success() {
		// given
		CommentRequest request = CommentRequest.builder()
			.postId(1L)
			.build();

		// when
		CommentPersistResponse result = commentFacade.createComment(request);

		// then
		assertEquals(3L, result.commentId());
	}

	@Test
	@DisplayName("getComments는 해당 게시글의 댓글 리스트를 가져온다")
	public void getComments_Success() {
		// given
		Long postId = 1L;

		// when
		CommentListResponse result = commentFacade.getComments(postId);

		// then
		List<CommentResponse> contents = result.contents();
		assertEquals(1, contents.size());
		assertEquals("test1", contents.get(0).content());
		assertEquals("홍길동", contents.get(0).author());
	}

	@Test
	@DisplayName("updateComment는 댓글을 수정한다")
	public void updateComment_Success() {
		// given
		Long commentId = 1L;
		CommentUpdateRequest request = CommentUpdateRequest.builder()
			.content("update")
			.build();

		// when
		commentFacade.updateComment(commentId, request);

		// then
		CommentListResponse result = commentFacade.getComments(1L);
		CommentResponse contents = result.contents().get(0);
		assertEquals("update", contents.content());
	}

	@Test
	@DisplayName("deleteComment는 해당 댓글을 삭제한다")
	public void deleteComment_Success() {
		// given
		Long commentId = 1L;

		// when
		commentFacade.deleteComment(commentId);

		// then
		CommentListResponse result = commentFacade.getComments(1L);
		assertEquals(0, result.contents().size());
	}

	@Test
	@DisplayName("getLastCleanupRunTime는 마지막 scheduling cleaning 시간을 조회할 수 있다")
	void getLastCleanupRunTime_Success() {
		// when
		String lastCleanupRunTime = commentFacade.getLastCleanupRunTime();

		// then
		assertEquals("아직 클린업 작업이 실행되지 않았습니다.", lastCleanupRunTime);
	}
}

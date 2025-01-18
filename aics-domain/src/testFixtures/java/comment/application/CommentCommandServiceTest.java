package comment.application;

import static kgu.developers.domain.post.domain.Category.NEWS;
import static kgu.developers.domain.user.domain.Major.CSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import mock.repository.FakeCommentRepository;
import mock.repository.FakePostRepository;
import mock.repository.FakeUserRepository;

public class CommentCommandServiceTest {
	private CommentCommandService commentCommandService;

	private static final Long TARGET_COMMENT_ID = 1L;
	private static final Long TEST_POST_ID = 1L;
	private static final String TEST_USER_ID = "202411345";

	@BeforeEach
	public void init() {
		FakePostRepository fakePostRepository = new FakePostRepository();
		FakeUserRepository fakeUserRepository = new FakeUserRepository();
		UserQueryService userQueryService = new UserQueryService(fakeUserRepository);

		initializeCommentCommandService(fakePostRepository, userQueryService);
		saveTestUserAndPost(fakeUserRepository, fakePostRepository);
		setTestSecurityContext(userQueryService);
	}

	private static void saveTestUserAndPost(FakeUserRepository fakeUserRepository,
		FakePostRepository fakePostRepository) {
		fakeUserRepository.save(
			User.create(TEST_USER_ID, "password1234", "홍길동", "honggildong@kyonggi.ac.kr",
				"010-1234-5678", CSE)
		);
		fakePostRepository.save(Post.create(
			"SW 부트캠프 4기 교육생 모집", "SW전문인재양성사업단에서는 SW부트캠프 4기 교육생을 모집합니다.", NEWS,
			User.builder().build()
		));
	}

	private void initializeCommentCommandService(FakePostRepository fakePostRepository,
		UserQueryService userQueryService) {
		commentCommandService = new CommentCommandService(
			new PostQueryService(fakePostRepository),
			userQueryService,
			new FakeCommentRepository()
		);
	}

	private static void setTestSecurityContext(UserQueryService userQueryService) {
		UserDetails user = userQueryService.getUserById(TEST_USER_ID);
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(
			new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities())
		);
	}

	@Test
	@DisplayName("createComment는 댓글을 생성할 수 있다.")
	public void createComment_Success() {
		// given
		String testCommentContent = "SW 부트캠프 모집이 정말 기대됩니다.";

		// when
		Long createdCommentId = commentCommandService.createComment(testCommentContent, TEST_POST_ID);

		// then
		assertEquals(TARGET_COMMENT_ID, createdCommentId);
	}

	@Test
	@DisplayName("updateComment는 댓글을 수정할 수 있다.")
	public void updateComment_Success() {
		// given
		Comment comment = Comment.create("SW 부트캠프 모집이 정말 기대됩니다.", User.builder().build(),
			Post.builder().build());
		String updatedCommentContent = "SW 부트캠프 모집이 정말 기대됩니다. 4기 교육생 확정이 언제일까요?";

		// when
		commentCommandService.updateComment(comment, updatedCommentContent);

		// then
		assertEquals(updatedCommentContent, comment.getContent());
	}

	@Test
	@DisplayName("deleteComment는 댓글을 삭제할 수 있다.")
	public void deleteComment_Success() {
		// given
		Comment comment = Comment.create("SW 부트캠프 모집이 정말 기대됩니다.", User.builder().build(),
			Post.builder().build());
		assertNull(comment.getDeletedAt(), "삭제 전에는 deletedAt이 null이어야 합니다");

		// when
		commentCommandService.deleteComment(comment);

		// then
		assertNotNull(comment.getDeletedAt());
	}
}

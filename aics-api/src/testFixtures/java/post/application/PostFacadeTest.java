package post.application;

import static kgu.developers.domain.post.domain.Category.NEWS;
import static kgu.developers.domain.user.domain.Major.CSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import kgu.developers.domain.file.application.query.FileQueryService;
import mock.repository.FakeFileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import kgu.developers.api.post.application.PostFacade;
import kgu.developers.api.post.presentation.response.PostSummaryPageResponse;
import kgu.developers.api.post.presentation.response.PostSummaryResponse;
import kgu.developers.common.response.PageableResponse;
import kgu.developers.domain.post.application.command.PostCommandService;
import kgu.developers.domain.post.application.query.PostQueryService;
import kgu.developers.domain.post.application.response.PostDetailResponse;
import kgu.developers.domain.post.application.response.PostTitleResponse;
import kgu.developers.domain.post.domain.Category;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import mock.repository.FakePostRepository;
import mock.repository.FakeUserRepository;

public class PostFacadeTest {
	private PostFacade postFacade;

	@BeforeEach
	public void init() {
		FakePostRepository fakePostRepository = new FakePostRepository();
		FakeUserRepository fakeUserRepository = new FakeUserRepository();
		FakeFileRepository fakeFileRepository = new FakeFileRepository();

		UserQueryService userQueryService = new UserQueryService(fakeUserRepository);
		FileQueryService fileQueryService = new FileQueryService(fakeFileRepository);

		postFacade = new PostFacade(
			new PostCommandService(userQueryService, fakePostRepository, fileQueryService),
			new PostQueryService(fakePostRepository)
		);

		User author = fakeUserRepository.save(User.builder()
			.id("202411345")
			.password("password1234")
			.name("홍길동")
			.email("test@kyonggi.ac.kr")
			.phone("010-1234-5678")
			.major(CSE)
			.build());

		UserDetails user = userQueryService.getUserById("202411345");
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(
			new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities())
		);

		fakePostRepository.save(Post.create(
			"first title", "first content", NEWS, author, null
		));

		Post delete = fakePostRepository.save(Post.create(
			"second title", "second content", NEWS, author, null
		));
		delete.delete();

		fakePostRepository.save(Post.create(
			"third title", "third content", NEWS, author, null
		));
	}

	@Test
	@DisplayName("getPostsByKeywordAndCategory는 삭제된 게시글을 제외하고 최신순으로 페이징 조회할 수 있다")
	public void getPostsByKeywordAndCategory_Success() {
		// given
		PageRequest request = PageRequest.of(0, 10);
		Category category = NEWS;

		// when
		PostSummaryPageResponse result = postFacade.getPostsByKeywordAndCategory(request, "title", category);

		// then
		List<PostSummaryResponse> resultData = result.contents();
		PageableResponse pageable = result.pageable();

		assertEquals(10, pageable.size());
		assertEquals(2, resultData.size());
		assertEquals("third title", resultData.get(0).title());
		assertEquals(category.getDescription(), resultData.get(0).category());
		assertEquals("first title", resultData.get(1).title());
		assertEquals(category.getDescription(), resultData.get(1).category());
	}

	@Test
	@DisplayName("getPostsByKeywordAndCategory는 잘못된 페이지 요청시 빈 목록을 반환한다")
	public void getPostsByKeywordAndCategory_InvalidPage() {
		// given
		PageRequest request = PageRequest.of(1, 10);
		Category category = NEWS;

		// when
		PostSummaryPageResponse result = postFacade.getPostsByKeywordAndCategory(request, "title", category);

		// then
		assertTrue(result.contents().isEmpty());
	}

	@Test
	@DisplayName("getPostByIdWithPrevAndNext는 현재 게시글과 이전, 다음 게시글 정보를 포함하여 조회한다")
	public void getPostByIdWithPrevAndNext_Success() {
		// given
		Long postId = 1L;

		// when
		PostDetailResponse result = postFacade.getPostByIdWithPrevAndNext(postId);

		// then
		assertEquals(postId, result.postId());
		assertEquals(NEWS.getDescription(), result.category());
		assertEquals("first title", result.title());
		assertEquals("first content", result.content());
		assertEquals("홍길동", result.author());

		assertNull(result.prevPost());

		PostTitleResponse nextPost = result.nextPost();
		assertEquals("third title", nextPost.title());
		assertEquals(3L, nextPost.postId());
	}
}

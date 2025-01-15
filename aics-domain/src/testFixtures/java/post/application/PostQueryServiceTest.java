package post.application;

import static kgu.developers.domain.post.domain.Category.NEWS;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.post.application.query.PostQueryService;
import kgu.developers.domain.post.application.response.PostDetailResponse;
import kgu.developers.domain.post.domain.Category;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.post.exception.PostNotFoundException;
import kgu.developers.domain.user.domain.User;
import mock.FakePostRepository;

public class PostQueryServiceTest {
	private PostQueryService postQueryService;
	private FakePostRepository fakePostRepository;

	@BeforeEach
	public void init() {
		fakePostRepository = new FakePostRepository();
		postQueryService = new PostQueryService(fakePostRepository);

		User author = User.builder().build();

		fakePostRepository.save(Post.create(
			"테스트용 제목1", "테스트용 내용1",
			NEWS, author
		));

		fakePostRepository.save(Post.create(
			"테스트용 제목2", "테스트용 내용2",
			NEWS, author
		));

		fakePostRepository.save(Post.create(
			"테스트용 제목3", "테스트용 내용3",
			NEWS, author
		));
	}

	@Test
	@DisplayName("getPostById는 해당 게시글과 이전, 다음 게시글을 조회할 수 있다")
	public void getPostById_Success() {
		// given
		Post post = fakePostRepository.findById(2L).orElse(null);

		// when
		PostDetailResponse response = postQueryService.getPostByIdWithPrevAndNext(post);

		// then
		assertEquals(post.getId(), response.postId());
		assertEquals(response.prevPost().postId(), 1L);
		assertEquals(response.prevPost().title(), "테스트용 제목1");
		assertEquals(response.nextPost().postId(), 3L);
		assertEquals(response.nextPost().title(), "테스트용 제목3");
	}

	@Test
	@DisplayName("getPostById는 마지막 게시글 조회 시 다음 게시글은 null이어야 한다")
	public void getPostById_LastPost_Success() {
		// given
		Long lastPostId = 3L;

		// when
		Post post = fakePostRepository.findById(lastPostId).orElse(null);
		PostDetailResponse response = postQueryService.getPostByIdWithPrevAndNext(post);

		// then
		assertEquals(lastPostId, response.postId());
		assertNull(response.nextPost());
		assertEquals(response.prevPost().postId(), 2L);
		assertEquals(response.prevPost().title(), "테스트용 제목2");
	}

	@Test
	@DisplayName("getById는 존재하지 않는 ID로 조회시 PostNotFoundException을 발생시킨다")
	public void getById_Throws_PostNotFoundException() {
		// given
		Long postId = 10L;

		// when
		// then
		assertThatThrownBy(
			() -> postQueryService.getById(postId)
		).isInstanceOf(PostNotFoundException.class);
	}

	@Test
	@DisplayName("getPostsByKeywordAndCategory는 게시글을 페이징 조회할 수 있다")
	public void getPostsByKeywordAndCategory_Success() {
		// given
		String keyword = "제목";
		Category category = NEWS;
		int page = 0;
		int size = 10;

		// when
		PaginatedListResponse<Post> posts = postQueryService.getPostsByKeywordAndCategory(
			PageRequest.of(page, size), keyword, category
		);

		// then
		assertEquals(3, posts.contents().size());
	}
}

package post.application;

import static kgu.developers.domain.post.domain.Category.NEWS;
import static kgu.developers.domain.post.domain.Category.NOTIFICATION;
import static kgu.developers.domain.user.domain.Major.CSE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import mock.repository.FakeFileRepository;
import mock.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.post.application.query.PostQueryService;
import kgu.developers.domain.post.application.response.PostDetailResponse;
import kgu.developers.domain.post.application.response.PostTitleResponse;
import kgu.developers.domain.post.domain.Category;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.post.exception.PostNotFoundException;
import kgu.developers.domain.user.domain.User;
import mock.repository.FakePostRepository;

public class PostQueryServiceTest {
	private PostQueryService postQueryService;
	private FakePostRepository fakePostRepository;
	private FakeFileRepository fakeFileRepository;
	private FakeUserRepository fakeUserRepository;

	@BeforeEach
	public void init() {
		fakePostRepository = new FakePostRepository();
		fakeFileRepository = new FakeFileRepository();
		fakeUserRepository = new FakeUserRepository();
		postQueryService = new PostQueryService(fakePostRepository, fakeFileRepository, fakeUserRepository);

		User author = fakeUserRepository.save(User.builder()
			.id("202411001")
			.password("password1234")
			.name("홍길동")
			.email("hong1@kyonggi.ac.kr")
			.phone("010-0000-0001")
			.major(CSE)
			.build());

		fakePostRepository.save(Post.create(
			"테스트용 제목1", "테스트용 내용1",
			NEWS, author.getId(), null, true
		));

		fakePostRepository.save(Post.create(
			"테스트용 제목2", "테스트용 내용2",
			NEWS, author.getId(), null, false
		));

		Post delete = fakePostRepository.save(Post.create(
			"테스트용 제목3", "테스트용 내용3",
			NEWS, author.getId(), null, false
		));
		delete.delete();

		fakePostRepository.save(Post.create(
			"테스트용 제목4", "테스트용 내용4",
			NOTIFICATION, author.getId(), null, false
		));

		fakePostRepository.save(Post.create(
			"테스트용 제목5", "테스트용 내용5",
			NEWS, author.getId(), null, false
		));
	}

	@Test
	@DisplayName("getPostById는 해당 게시글과 이전, 다음 게시글을 조회할 수 있다")
	public void getPostById_Success() {
		// given
		Post post = fakePostRepository.findByIdAndDeletedAtIsNull(2L).orElse(null);

		// when
		PostDetailResponse result = postQueryService.getPostByIdWithPrevAndNext(post);

		// then
		PostTitleResponse prev = result.prevPost();
		PostTitleResponse next = result.nextPost();

		assertEquals(result.postId(), post.getId());
		assertNull(post.getDeletedAt());
		assertEquals(1L, prev.postId());
		assertEquals("테스트용 제목1", prev.title());
		assertEquals(5L, next.postId());
		assertEquals("테스트용 제목5", next.title());
	}

	@Test
	@DisplayName("getPostById는 마지막 게시글 조회 시 다음 게시글은 null이어야 한다")
	public void getPostById_LastPost_Success() {
		// given
		Long lastPostId = 5L;

		// when
		Post post = fakePostRepository.findByIdAndDeletedAtIsNull(lastPostId).orElse(null);
		PostDetailResponse result = postQueryService.getPostByIdWithPrevAndNext(post);

		// then
		PostTitleResponse prev = result.prevPost();

		assertEquals(lastPostId, result.postId());
		assertNull(result.nextPost());
		assertEquals(2L, prev.postId());
		assertEquals("테스트용 제목2", prev.title());
	}

	@Test
	@DisplayName("getById는 존재하지 않는 ID로 조회시 PostNotFoundException을 발생시킨다")
	public void getById_Throws_PostNotFoundException() {
		// given
		Long postId = 10L;

		// when
		// then
		assertThatThrownBy(() -> postQueryService.getById(postId))
			.isInstanceOf(PostNotFoundException.class);
	}

	@Test
	@DisplayName("getPostsByKeywordAndCategory는 게시글을 페이징 조회할 수 있다")
	public void getPostsByKeywordAndCategory_Success() {
		// given
		List<String> keywords = List.of("제목");
		Category category = NEWS;
		int page = 0;
		int size = 10;

		// when
		PaginatedListResponse<Post> result = postQueryService.getPostsByKeywordAndCategory(
			PageRequest.of(page, size), keywords, category
		);

		// then
		assertEquals(3, result.contents().size());
	}
}

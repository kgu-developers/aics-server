package post.domain;

import static kgu.developers.domain.post.domain.Category.NEWS;
import static kgu.developers.domain.post.domain.Category.NOTIFICATION;
import static kgu.developers.domain.user.domain.Major.CSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.post.domain.Category;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.user.domain.User;

public class PostDomainTest {
	private Post post;
	private static final String TITLE = "Valid Title";
	private static final String CONTENT = "This is valid content.";

	@BeforeEach
	public void init() {
		User author = user();
		post = Post.create(TITLE, CONTENT, NEWS, author);
	}

	private User user() {
		return User.create("202411345",
			"password",
			"홍길동",
			"valid@kyonggi.ac.kr",
			"010-1234-5678", CSE);
	}

	@Test
	@DisplayName("POST 객체를 생성할 수 있다")
	public void createPost_Success() {

		// when
		// then
		assertNotNull(post);
		assertEquals(TITLE, post.getTitle());
		assertEquals(CONTENT, post.getContent());
		assertEquals(0, post.getViews());
		assertFalse(post.isPinned());
		assertEquals(NEWS, post.getCategory());
	}

	@Test
	@DisplayName("POST 제목을 업데이트할 수 있다")
	public void updatePostTitle_Success() {
		// given
		String newTitle = "Updated Title";

		// when
		post.updateTitle(newTitle);

		// then
		assertEquals(newTitle, post.getTitle());
	}

	@Test
	@DisplayName("POST 내용을 업데이트할 수 있다")
	public void updatePostContent_Success() {
		// given
		String newContent = "Updated Content";

		// when
		post.updateContent(newContent);

		// then
		assertEquals(newContent, post.getContent());
	}

	@Test
	@DisplayName("POST 카테고리를 업데이트할 수 있다")
	public void updatePostCategory_Success() {
		// given
		Category newCategory = NOTIFICATION;

		// when
		post.updateCategory(newCategory);

		// then
		assertEquals(newCategory, post.getCategory());
	}

	@Test
	@DisplayName("POST 조회수를 증가시킬 수 있다")
	public void increasePostViews_Success() {
		// given
		int incrementCount = 5;

		// when
		IntStream.range(0, incrementCount)
			.forEach(i -> post.increaseViews());

		// then
		assertEquals(incrementCount, post.getViews());
	}

	@Test
	@DisplayName("POST 고정 여부를 토글할 수 있다")
	public void togglePostPinned_Success() {

		// when
		post.togglePinned();
		// then
		assertTrue(post.isPinned());

		// when
		post.togglePinned();
		// then
		assertFalse(post.isPinned());
	}
}

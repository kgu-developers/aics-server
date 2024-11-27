package post.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import kgu.developers.domain.post.domain.Category;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.user.domain.Major;
import kgu.developers.domain.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class PostDomainTest {
	@Test
	@DisplayName("POST 객체를 생성할 수 있다")
	public void createPost_Success() {
		// given
		String title = "Valid Title";
		String content = "This is valid content.";
		Category category = Category.DEPT_INFO;
		User user = getUser();

		// when
		Post post = Post.create(title, content, category, user);

		// then
		assertNotNull(post);
		assertEquals(title, post.getTitle());
		assertEquals(content, post.getContent());
		assertEquals(0, post.getViews());
		assertFalse(post.isPinned());
		assertEquals(category, post.getCategory());
		assertEquals(user, post.getAuthor());
	}

	@Test
	@DisplayName("POST 제목을 업데이트할 수 있다")
	public void updatePostTitle_Success() {
		// given
		String title = "Valid Title";
		String content = "This is valid content.";
		Category category = Category.DEPT_INFO;
		User user = getUser();

		Post post = Post.create(title, content, category, user);
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
		String title = "Valid Title";
		String content = "This is valid content.";
		Category category = Category.DEPT_INFO;
		User user = getUser();

		Post post = Post.create(title, content, category, user);
		String newContent = "Updated Content";

		// when
		post.updateContent(newContent);

		// then
		assertEquals(newContent, post.getContent());
	}

	@Test
	@DisplayName("POST 조회수를 증가시킬 수 있다")
	public void increasePostViews_Success() {
		// given
		String title = "Valid Title";
		String content = "This is valid content.";
		Category category = Category.DEPT_INFO;
		User user = getUser();

		Post post = Post.create(title, content, category, user);
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
		// given
		String title = "Valid Title";
		String content = "This is valid content.";
		Category category = Category.DEPT_INFO;
		User user = getUser();

		Post post = Post.create(title, content, category, user);

		// when
		post.togglePinned();
		// then
		assertTrue(post.isPinned());

		// when
		post.togglePinned();
		// then
		assertFalse(post.isPinned());
	}

	public User getUser() {
		String id = "202411345";
		String password = "password";
		String name = "홍길동";
		String email = "valid@kyonggi.ac.kr";
		String phone = "010-1234-5678";
		Major major = Major.CSE;

		return User.create(id, password, name, email, phone, major);
	}

}

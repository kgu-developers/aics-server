package comment.domain;

import static kgu.developers.domain.post.domain.Category.DEPT_INFO;
import static kgu.developers.domain.user.domain.Major.CSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.user.domain.User;

public class CommentDomainTest {
	@Test
	@DisplayName("COMMENT 객체를 생성할 수 있다")
	public void createComment_Success() {
		// given
		String content = "success";
		User author = getUser();
		Post post = getPost();

		// when
		Comment comment = Comment.create(content, author, post);

		// then
		assertNotNull(comment);
		assertEquals(content, comment.getContent());
		assertEquals(author, comment.getAuthor());
		assertEquals(post, comment.getPost());
	}

	@Test
	@DisplayName("COMMENT 객체를 수정할 수 있다")
	public void updateComment_Success() {
		String updateContent = "update";

		String content = "create";
		User author = getUser();
		Post post = getPost();
		Comment comment = Comment.create(content, author, post);

		// when
		comment.updateContent(updateContent);

		// then
		assertEquals(updateContent, comment.getContent());
	}

	private User getUser() {
		String id = "202411345";
		String password = "password";
		String name = "홍길동";
		String email = "valid@kyonggi.ac.kr";
		String phone = "010-1234-5678";

		return User.create(id, password, name, email, phone, CSE);
	}

	private Post getPost() {
		String title = "Valid Title";
		String content = "This is valid content.";
		User user = getUser();

		return Post.create(title, content, DEPT_INFO, user);
	}
}

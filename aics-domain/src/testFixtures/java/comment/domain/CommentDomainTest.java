package comment.domain;

import static kgu.developers.domain.post.domain.Category.NEWS;
import static kgu.developers.domain.user.domain.Major.CSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.user.domain.User;

public class CommentDomainTest {
	private Comment comment;
	private static final String CONTENT = "content";
	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	private User getUser() {
		return User.create(
			"202411345",
			"password",
			"홍길동",
			"valid@kyonggi.ac.kr",
			"010-1234-5678",
			CSE,
			PASSWORD_ENCODER
		);
	}

	@BeforeEach
	public void init() {
		User author = getUser();
		Post post = getPost(author);
		comment = Comment.create(CONTENT, author, post);
	}

	private Post getPost(User author) {
		return Post.create(
			"title",
			"content.",
			NEWS,
			author,
			null,
			false
		);
	}

	@Test
	@DisplayName("COMMENT 객체를 생성할 수 있다")
	public void createComment_Success() {
		// given
		// when
		// then
		assertNotNull(comment);
		assertEquals(CONTENT, comment.getContent());
	}

	@Test
	@DisplayName("COMMENT 객체를 수정할 수 있다")
	public void updateComment_Success() {
		// given
		String updateContent = "update";

		// when
		comment.updateContent(updateContent);

		// then
		assertEquals(updateContent, comment.getContent());
	}
}

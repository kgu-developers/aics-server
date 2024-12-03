package comment.domain;

import static kgu.developers.domain.post.domain.Category.DEPT_INFO;
import static kgu.developers.domain.user.domain.Major.CSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.user.domain.User;

public class CommentDomainTest {
	private User author;
	private Post post;

	@BeforeEach
	public void init() {
		author = User.create(
			"202411345",
			"password",
			"홍길동",
			"valid@kyonggi.ac.kr",
			"010-1234-5678",
			CSE
		);

		post = Post.create(
			"title",
			"content.",
			DEPT_INFO,
			author
		);
	}

	@Test
	@DisplayName("COMMENT 객체를 생성할 수 있다")
	public void createComment_Success() {
		// given
		String content = "success";

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
		// given
		String updateContent = "update";

		String content = "create";
		Comment comment = Comment.create(content, author, post);

		// when
		comment.updateContent(updateContent);

		// then
		assertEquals(updateContent, comment.getContent());
	}
}

package kgu.developers.domain.comment.domain;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String content;

	@ManyToOne(fetch = EAGER)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "author_id", nullable = false)
	private User author;

	public static Comment create(String content, User author, Post post) {
		return Comment.builder()
			.content(content)
			.author(author)
			.post(post)
			.build();
	}

	public void updateContent(String content) {
		this.content = content;
	}

}

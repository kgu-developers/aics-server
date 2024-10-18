package kgu.developers.core.domain.comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kgu.developers.core.common.domain.BaseTimeEntity;
import kgu.developers.core.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseTimeEntity {
	@Id
	@Column(name = "comment_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String content;

	/*
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;
	*/

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "author_id", nullable = false)
	private User author;

	public static Comment create(String content, User author) {
		return Comment.builder()
			.content(content)
			.author(author)
			.build();
	}

}

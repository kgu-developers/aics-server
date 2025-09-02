package kgu.developers.domain.comment.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kgu.developers.common.domain.BaseTimeEntity;
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

	@Column(nullable = false)
	private Long postId;

	@Column(nullable = false)
	private String authorId;

	public static Comment create(String content, String authorId, Long postId) {
		return Comment.builder()
			.content(content)
			.authorId(authorId)
			.postId(postId)
			.build();
	}

	public void updateContent(String content) {
		this.content = content;
	}

}

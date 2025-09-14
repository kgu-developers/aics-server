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

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

	private Long id;

	private String content;

	private Long postId;

	private String authorId;

	protected LocalDateTime createdAt;
	protected LocalDateTime updatedAt;
	protected LocalDateTime deletedAt;

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

	public void delete() {
		this.deletedAt = LocalDateTime.now();
	}

}

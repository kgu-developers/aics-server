package kgu.developers.domain.post.domain;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
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
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Post extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String title;

	@Column(nullable = false, columnDefinition = "text")
	private String content;

	@Column(nullable = false)
	private int views;

	@Enumerated(STRING)
	private Category category;

	@Column(nullable = false, length = 10)
	private String authorId;

	@Column(nullable = false)
	private boolean isPinned;

	private Long fileId;

	public static Post create(String title, String content, Category category, String authorId, Long fileId, boolean isPinned) {
		return Post.builder()
			.title(title)
			.content(content)
			.views(0)
			.isPinned(isPinned)
			.category(category)
			.authorId(authorId)
			.fileId(fileId)
			.build();
	}

	public void updateTitle(String title) {
		this.title = title;
	}

	public void updateContent(String content) {
		this.content = content;
	}

	public void updateCategory(Category category) {
		this.category = category;
	}

	public void updatePinned(boolean isPinned) {
		this.isPinned = isPinned;
	}

	public void increaseViews() {
		this.views++;
	}

	public void updateFileId(Long fileId) {
		this.fileId = fileId;
	}

	public void togglePinned() {
		this.isPinned = !this.isPinned;
	}
}

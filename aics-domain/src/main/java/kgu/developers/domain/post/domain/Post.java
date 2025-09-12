package kgu.developers.domain.post.domain;

import static lombok.AccessLevel.PROTECTED;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Post {

	private Long id;

	private String title;

	private String content;

	private int views;

	private Category category;

	private String authorId;

	private boolean isPinned;

	private Long fileId;

	protected LocalDateTime createdAt;
	protected LocalDateTime updatedAt;
	protected LocalDateTime deletedAt;

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

	public void delete(){
		deletedAt = LocalDateTime.now();
	}
}

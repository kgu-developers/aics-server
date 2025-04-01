package kgu.developers.domain.post.domain;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.file.domain.FileEntity;
import kgu.developers.domain.user.domain.User;
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

	@ManyToOne(fetch = EAGER)
	@JoinColumn(name = "author_id")
	private User author;

	@Column(nullable = false)
	private boolean isPinned;

	@OneToOne
	@JoinColumn(name = "file_id")
	private FileEntity file;

	@Builder.Default
	@OneToMany(mappedBy = "post", fetch = LAZY, cascade = ALL, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();

	public static Post create(String title, String content, Category category, User author, FileEntity file, boolean isPinned) {
		return Post.builder()
			.title(title)
			.content(content)
			.views(0)
			.isPinned(isPinned)
			.category(category)
			.author(author)
			.file(file)
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

	public void updateFile(FileEntity file) {
		this.file = file;
	}

	public void togglePinned() {
		this.isPinned = !this.isPinned;
	}
}

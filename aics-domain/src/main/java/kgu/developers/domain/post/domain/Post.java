package kgu.developers.domain.post.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.comment.Comment;
import kgu.developers.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Post extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "post_id")
	private Long id;

	@Column(nullable = false, length = 100)
	private String title;

	@Column(nullable = false, columnDefinition = "text")
	private String content;

	@Column(nullable = false)
	private int views;

	@Enumerated(STRING)
	private Category category;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User author;

	@Builder.Default
	@OneToMany(mappedBy = "post", fetch = LAZY, cascade = ALL, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();

    /* TODO: 파일 엔티티 생성 후 연결 & create 메서드에 추가
    @OneToOne
    @JoinColumn(name = "file_id")
    private FileEntity attachment;

    public boolean hasAttachment(){
		return attachment != null;
	}
    */

	public static Post create(String title, String content, User author) {
		return Post.builder()
			.title(title)
			.content(content)
			.views(0)
			.author(author) // NOTE: User Setter 주입 방지 위해 생성자 주입
			.build();
	}

	public void updateTitle(String title) {
		this.title = title;
	}

	public void updateContent(String content) {
		this.content = content;
	}

	public boolean isAuthor(String authorId) {
		return this.author.getUserId().equals(authorId);
	}
}

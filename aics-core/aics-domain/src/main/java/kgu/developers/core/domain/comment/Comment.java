package kgu.developers.core.domain.comment;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kgu.developers.core.common.domain.BaseTimeEntity;
import kgu.developers.core.domain.user.User;
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
	@Column(name = "comment_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String content;

	/* todo : 주석 해제
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;
	*/

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "author_id", nullable = false)
	private User author;

	public static Comment create(String content, User author/*todo: 주석 해제 ,Post post*/) {
		return Comment.builder().content(content).author(author)
			//todo: 주석해제 .post(post)
			.build();
	}

}

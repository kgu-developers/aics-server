package mock.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.comment.domain.CommentRepository;

public class FakeCommentRepository implements CommentRepository {
	private final List<Comment> data = Collections.synchronizedList(new ArrayList<>());
	private final AtomicLong sequence = new AtomicLong(1);

	@Override
	public Comment save(Comment comment) {

		Comment savedComment = Comment.builder()
			.id(comment.getId() == null ? sequence.getAndIncrement() : comment.getId())
			.content(comment.getContent())
			.authorId(comment.getAuthorId())
			.postId(comment.getPostId())
			.createdAt(getExistingCreatedAt(comment.getId()))
			.deletedAt(comment.getDeletedAt())
			.build();

		if (comment.getId() != null) {
			data.removeIf(p -> p.getId().equals(comment.getId()));
		}

		data.add(savedComment);
		return savedComment;
	}

	private LocalDateTime getExistingCreatedAt(Long id) {
		return data.stream()
			.filter(p -> p.getId().equals(id))
			.findFirst()
			.map(Comment::getCreatedAt)
			.orElse(LocalDateTime.now());
	}

	@Override
	public List<Comment> findAllByPostIdAndDeletedAtIsNull(Long postId) {
		return data.stream()
			.filter(comment -> comment.getPostId().equals(postId)
				&& comment.getDeletedAt() == null)
			.toList();
	}

	@Override
	public Optional<Comment> findByIdAndDeletedAtIsNull(Long commentId) {
		return data.stream()
			.filter(comment -> comment.getId().equals(commentId)
				&& comment.getDeletedAt() == null)
			.findFirst();
	}

	@Override
	public void deleteAllByDeletedAtBefore(int retentionDays) {
		LocalDateTime thresholdDate = LocalDateTime.now().minusDays(retentionDays);

		data.removeIf(comment -> comment.getDeletedAt() != null
			&& comment.getDeletedAt().isBefore(thresholdDate));
	}
}

package mock.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import kgu.developers.domain.comment.domain.Comment;
import kgu.developers.domain.comment.domain.CommentRepository;
import mock.TestEntityUtils;

public class FakeCommentRepository implements CommentRepository {
	private final List<Comment> data = Collections.synchronizedList(new ArrayList<>());
	private final AtomicLong sequence = new AtomicLong(1);

	@Override
	public Comment save(Comment comment) {
		Comment newComment = Comment.builder()
			.id(sequence.getAndIncrement())
			.content(comment.getContent())
			.author(comment.getAuthor())
			.post(comment.getPost())
			.build();

		TestEntityUtils.setCreatedAt(newComment, LocalDateTime.now());

		data.add(newComment);
		return newComment;
	}

	@Override
	public List<Comment> findAllByPostIdAndDeletedAtIsNull(Long postId) {
		return data.stream()
			.filter(comment -> comment.getPost().getId().equals(postId)
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

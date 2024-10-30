package kgu.developers.domain.comment.infrastructure;

import kgu.developers.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCommentRepository extends JpaRepository<Comment, Long> {
}

package kgu.developers.domain.comment.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.comment.domain.Comment;

public interface JpaCommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAllByPostIdAndDeletedAtIsNull(Long postId);

}

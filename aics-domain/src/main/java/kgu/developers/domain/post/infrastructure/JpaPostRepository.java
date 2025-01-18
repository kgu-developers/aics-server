package kgu.developers.domain.post.infrastructure;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.post.domain.Category;
import kgu.developers.domain.post.domain.Post;

public interface JpaPostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findFirstByCreatedAtLessThanAndDeletedAtIsNullAndCategoryOrderByCreatedAtDesc(
		LocalDateTime createdAt, Category category);

	Optional<Post> findFirstByCreatedAtGreaterThanAndDeletedAtIsNullAndCategoryOrderByCreatedAtAsc(
		LocalDateTime createdAt, Category category);

	Optional<Post> findByIdAndDeletedAtIsNull(Long id);
}

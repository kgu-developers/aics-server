package kgu.developers.domain.post.infrastructure;

import kgu.developers.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaPostRepository extends JpaRepository<Post, Long> {

	@Query("SELECT p FROM Post p " +
		"LEFT JOIN FETCH p.author " +
		"LEFT JOIN FETCH p.comments c " +
		"LEFT JOIN FETCH c.author " +
		"WHERE p.id = :postId")
	Optional<Post> findByIdWithAuthorAndComments(Long postId);
}

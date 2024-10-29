package kgu.developers.domain.post.infrastructure;

import kgu.developers.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostRepository extends JpaRepository<Post, Long> {
}

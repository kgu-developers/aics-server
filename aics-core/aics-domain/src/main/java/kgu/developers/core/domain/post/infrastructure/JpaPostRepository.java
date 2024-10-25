package kgu.developers.core.domain.post.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.core.domain.post.Post;

public interface JpaPostRepository extends JpaRepository<Post, Long> {
}

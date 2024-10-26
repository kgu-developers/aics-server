package kgu.developers.domain.post.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.post.domain.Post;

public interface JpaPostRepository extends JpaRepository<Post, Long> {
}

package kgu.developers.domain.thesis.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.thesis.infrastructure.entity.ThesisJpaEntity;

public interface JpaThesisRepository extends JpaRepository<ThesisJpaEntity, Long> {
}

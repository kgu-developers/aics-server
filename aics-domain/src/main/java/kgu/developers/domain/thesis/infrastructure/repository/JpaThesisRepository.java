package kgu.developers.domain.thesis.infrastructure.repository;

import kgu.developers.domain.thesis.domain.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.thesis.infrastructure.entity.ThesisJpaEntity;

import java.util.Optional;

public interface JpaThesisRepository extends JpaRepository<ThesisJpaEntity, Long> {
    Optional<Thesis> findByIdAndDeletedAtIsNull(Long thesisId);
}

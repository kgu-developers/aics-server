package kgu.developers.domain.thesis.infrastructure.repository;

import kgu.developers.domain.thesis.infrastructure.entity.ThesisJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaThesisRepository extends JpaRepository<ThesisJpaEntity, Long> {
    Optional<ThesisJpaEntity> findByIdAndDeletedAtIsNull(Long thesisId);
    @Query("SELECT c.approval FROM ThesisJpaEntity c WHERE c.id = :id AND c.deletedAt IS NULL")
    Optional<Boolean> findApprovalByIdAndDeletedAtIsNull(Long id);
}

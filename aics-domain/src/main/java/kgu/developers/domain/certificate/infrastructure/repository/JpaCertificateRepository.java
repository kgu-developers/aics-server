package kgu.developers.domain.certificate.infrastructure.repository;

import kgu.developers.domain.certificate.domain.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.certificate.infrastructure.entity.CertificateJpaEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaCertificateRepository extends JpaRepository<CertificateJpaEntity, Long> {
    Optional<CertificateJpaEntity> findByIdAndDeletedAtIsNull(Long id);
    @Query("SELECT c.approval FROM CertificateJpaEntity c WHERE c.id = :id AND c.deletedAt IS NULL")
    Optional<Boolean> findApprovalById(Long id);
}

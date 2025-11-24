package kgu.developers.domain.certificate.infrastructure.repository;

import kgu.developers.domain.certificate.domain.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.certificate.infrastructure.entity.CertificateJpaEntity;

import java.util.Optional;

public interface JpaCertificateRepository extends JpaRepository<CertificateJpaEntity, Long> {
    Optional<Certificate> findByIdAndDeletedAtIsNull(Long id);
}

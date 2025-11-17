package kgu.developers.domain.certificate.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.certificate.infrastructure.entity.CertificateJpaEntity;

public interface JpaCertificateRepository extends JpaRepository<CertificateJpaEntity, Long> {
}

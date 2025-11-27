package kgu.developers.domain.certificate.domain;

import kgu.developers.domain.certificate.infrastructure.entity.CertificateJpaEntity;

import java.util.Optional;

public interface CertificateRepository {
	Long save(Certificate certificate);

	Optional<Certificate> findByIdAndDeletedAtIsNull(Long id);

    Optional<Boolean> findApprovalByIdAndDeletedAtIsNull(Long id);
}

package kgu.developers.domain.certificate.domain;

import java.util.Optional;

public interface CertificateRepository {
	Long save(Certificate certificate);

	Optional<Certificate> findByIdAndDeletedAtIsNull(Long id);

    Optional<Boolean> findApprovalByIdAndDeletedAtIsNull(Long id);
}

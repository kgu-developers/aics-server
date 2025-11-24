package kgu.developers.domain.certificate.domain;

import java.util.Optional;

public interface CertificateRepository {
	Long save(Certificate certificate);

	Optional<Certificate> findByIdAndDeletedIsNull(Long id);
}

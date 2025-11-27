package kgu.developers.domain.certificate.infrastructure.repository;

import org.springframework.stereotype.Repository;

import kgu.developers.domain.certificate.domain.Certificate;
import kgu.developers.domain.certificate.domain.CertificateRepository;
import kgu.developers.domain.certificate.infrastructure.entity.CertificateJpaEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CertificateRepositoryImpl implements CertificateRepository {
	private final JpaCertificateRepository jpaCertificateRepository;

	@Override
	public Long save(Certificate certificate) {
		return jpaCertificateRepository.save(CertificateJpaEntity.toEntity(certificate)).getId();
	}

	@Override
	public Optional<Certificate> findByIdAndDeletedAtIsNull(Long id) {
		return jpaCertificateRepository.findByIdAndDeletedAtIsNull(id)
				.map(CertificateJpaEntity::toDomain);
	}

	@Override
	public Optional<Boolean> findApprovalByIdAndDeletedAtIsNull(Long id) {
		return jpaCertificateRepository.findApprovalById(id);
	}
}

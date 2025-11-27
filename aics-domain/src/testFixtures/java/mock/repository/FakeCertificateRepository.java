package mock.repository;

import kgu.developers.domain.certificate.domain.Certificate;
import kgu.developers.domain.certificate.domain.CertificateRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FakeCertificateRepository implements CertificateRepository {
    private final List<Certificate> data = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public Long save(Certificate certificate) {
        Certificate savedCertificate = Certificate.builder()
            .id(sequence.getAndIncrement())
            .certificateFileId(certificate.getCertificateFileId())
            .approval(certificate.isApproval())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deletedAt(null)
            .build();

        data.add(savedCertificate);
        return savedCertificate.getId();
    }

    @Override
    public Optional<Certificate> findByIdAndDeletedAtIsNull(Long id) {
        return data.stream()
            .filter(certificate -> certificate.getId().equals(id))
            .filter(certificate -> certificate.getDeletedAt() == null)
            .findFirst();
    }

    @Override
    public Optional<Boolean> findApprovalByIdAndDeletedAtIsNull(Long id) {
        return data.stream()
            .filter(certificate -> certificate.getId().equals(id))
            .filter(certificate -> certificate.getDeletedAt() == null)
            .findFirst()
            .map(Certificate::isApproved);
    }
}

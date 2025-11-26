package kgu.developers.domain.certificate.application.query;

import kgu.developers.domain.certificate.domain.CertificateRepository;
import kgu.developers.domain.certificate.exception.CertificateNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificateQueryService {
    private final CertificateRepository certificateRepository;

    public boolean isApproved(Long id) {
        return certificateRepository.findApprovalByIdAndDeletedAtIsNull(id)
                .orElseThrow(CertificateNotFoundException::new);
    }
}

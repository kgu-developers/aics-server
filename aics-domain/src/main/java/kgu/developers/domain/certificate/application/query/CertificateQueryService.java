package kgu.developers.domain.certificate.application.query;

import kgu.developers.domain.certificate.domain.Certificate;
import kgu.developers.domain.certificate.domain.CertificateRepository;
import kgu.developers.domain.certificate.exception.CertificateNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificateQueryService {
    private final CertificateRepository certificateRepository;

    public Certificate getById(Long id) {
        return  certificateRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(CertificateNotFoundException::new);
    }
}

package kgu.developers.admin.certificate.application;

import kgu.developers.admin.certificate.presentation.response.CertificateDetailResponse;
import kgu.developers.domain.certificate.application.query.CertificateQueryService;
import kgu.developers.domain.certificate.domain.Certificate;
import kgu.developers.domain.file.application.query.FileQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class CertificateAdminFacade {
    private final CertificateQueryService certificateQueryService;
    private final FileQueryService fileQueryService;

    public CertificateDetailResponse getById(Long id){
        Certificate certificate = certificateQueryService.getById(id);
        String physicalPath = certificate.getCertificateFileId() != null
                ? fileQueryService.getFilePhysicalPath(certificate.getCertificateFileId())
                : null;
        return CertificateDetailResponse.from(certificate, physicalPath);
    }
}

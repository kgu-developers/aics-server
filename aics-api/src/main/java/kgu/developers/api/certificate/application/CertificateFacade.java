package kgu.developers.api.certificate.application;

import kgu.developers.api.certificate.presentation.response.CertificateDetailResponse;
import kgu.developers.domain.certificate.application.command.CertificateCommandService;
import kgu.developers.domain.certificate.application.query.CertificateQueryService;
import kgu.developers.domain.certificate.domain.Certificate;
import kgu.developers.domain.file.application.query.FileQueryService;
import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.user.application.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class CertificateFacade {

    private final CertificateCommandService certificateCommandService;
    private final UserQueryService userQueryService;
    private final GraduationUserQueryService graduationUserQueryService;
    private final GraduationUserCommandService graduationUserCommandService;
    private final CertificateQueryService certificateQueryService;
    private final FileQueryService fileQueryService;

    public Long submitCertificate(MultipartFile file, Long scheduleId) {
        Long certificateId = certificateCommandService.submitCertificate(file,scheduleId);
        String userId = userQueryService.getMyId();
        GraduationUser graduationUser = graduationUserQueryService.getByUserId(userId);
        graduationUserCommandService.updateCertificate(graduationUser, certificateId);
        return certificateId;
    }
    public CertificateDetailResponse getById(Long id){
        Certificate certificate = certificateQueryService.getById(id);
        String physicalPath = certificate.getCertificateFileId() != null
                ? fileQueryService.getFilePhysicalPath(certificate.getCertificateFileId())
                : null;
        return CertificateDetailResponse.from(certificate, physicalPath);
    }
}

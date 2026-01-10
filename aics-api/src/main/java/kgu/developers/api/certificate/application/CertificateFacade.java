package kgu.developers.api.certificate.application;

import kgu.developers.domain.certificate.application.command.CertificateCommandService;
import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class CertificateFacade {

    private final CertificateCommandService certificateCommandService;
    private final GraduationUserQueryService graduationUserQueryService;
    private final GraduationUserCommandService graduationUserCommandService;

    public Long submitCertificate(MultipartFile file, Long scheduleId) {
        Long certificateId = certificateCommandService.submitCertificate(file,scheduleId);
        GraduationUser graduationUser = graduationUserQueryService.me();
        graduationUserCommandService.updateCertificate(graduationUser, certificateId);
        return certificateId;
    }
}

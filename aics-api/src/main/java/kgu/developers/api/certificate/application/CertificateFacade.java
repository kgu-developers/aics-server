package kgu.developers.api.certificate.application;

import kgu.developers.domain.certificate.application.command.CertificateCommandService;
import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.user.application.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CertificateFacade {

    private final CertificateCommandService certificateCommandService;
    private final UserQueryService userQueryService;
    private final GraduationUserQueryService graduationUserQueryService;
    private final GraduationUserCommandService graduationUserCommandService;

    @Transactional
    public Long submitCertificate(MultipartFile file, Long scheduleId) {
        Long certificateId = certificateCommandService.submitCertificate(file,scheduleId);
        String userId = userQueryService.getMyId();
        GraduationUser graduationUser = graduationUserQueryService.getByStudentId(userId);
        graduationUserCommandService.updateCertificate(graduationUser, certificateId);
        return certificateId;
    }
}

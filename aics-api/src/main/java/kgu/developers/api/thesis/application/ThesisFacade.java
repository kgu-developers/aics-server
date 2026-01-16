package kgu.developers.api.thesis.application;

import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.schedule.domain.SubmissionType;
import kgu.developers.domain.thesis.application.command.ThesisCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class ThesisFacade {

    private final ThesisCommandService thesisCommandService;
    private final GraduationUserQueryService graduationUserQueryService;
    private final GraduationUserCommandService graduationUserCommandService;

    public Long submitThesis(MultipartFile file, SubmissionType thesisType) {
        Long thesisId = thesisCommandService.submitThesis(file,thesisType);
        GraduationUser graduationUser = graduationUserQueryService.me();

        graduationUserCommandService.updateThesis(graduationUser, thesisId, thesisType);
        return thesisId;
    }

}

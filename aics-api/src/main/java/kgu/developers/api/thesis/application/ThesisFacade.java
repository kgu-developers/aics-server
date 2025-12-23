package kgu.developers.api.thesis.application;

import kgu.developers.domain.graduationUser.application.command.GraduationUserCommandService;
import kgu.developers.domain.graduationUser.application.query.GraduationUserQueryService;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.schedule.application.query.ScheduleQueryService;
import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.thesis.application.command.ThesisCommandService;
import kgu.developers.domain.user.application.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class ThesisFacade {

    private final ThesisCommandService thesisCommandService;
    private final UserQueryService userQueryService;
    private final GraduationUserQueryService graduationUserQueryService;
    private final ScheduleQueryService scheduleQueryService;
    private final GraduationUserCommandService graduationUserCommandService;

    public Long submitThesis(MultipartFile file, Long scheduleId) {
        Long thesisId = thesisCommandService.submitThesis(file,scheduleId);
        String userId = userQueryService.getMyId();
        GraduationUser graduationUser = graduationUserQueryService.getByUserId(userId);

        Schedule schedule = scheduleQueryService.getScheduleManagement(scheduleId);

        graduationUserCommandService.updateThesis(graduationUser, thesisId, schedule);
        return thesisId;
    }

}

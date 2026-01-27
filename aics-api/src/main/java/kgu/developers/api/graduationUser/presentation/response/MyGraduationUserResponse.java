package kgu.developers.api.graduationUser.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.schedule.domain.SubmissionType;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
public record MyGraduationUserResponse(
        @Schema(description = "제목", example = "최종 보고서를 제출하지 않았어요.")
        String title,

        @Schema(description = "내용", example = "최종 보고서 마감 기한은 2025년 10월 30일이에요.")
        String description
) {
    private static final DateTimeFormatter KOREAN_DATE = DateTimeFormatter.ofPattern("yyyy년 M월 d일");


    public static MyGraduationUserResponse from(GraduationUser graduationUser, Schedule schedule) {
        GraduationUserStatus status = determineStatus(graduationUser);

        String title = switch (status) {
            case GRADUATION_TYPE_NOT_SUBMITTED -> "졸업 유형을 아직 선택하지 않았어요.";
            case PROFESSOR_NOT_ASSIGNED -> "지도교수가 아직 배정되지 않았어요.";
            case MID_THESIS_NOT_SUBMITTED -> "중간 보고서를 제출하지 않았어요.";
            case FINAL_THESIS_NOT_SUBMITTED -> "최종 보고서를 제출하지 않았어요.";
            case CERTIFICATE_NOT_SUBMITTED -> "자격증을 제출하지 않았어요.";
            case GRADUATION_REQUIREMENTS_MET -> "졸업 요건을 충족했어요.";
        };

        String description = switch (status) {
            case MID_THESIS_NOT_SUBMITTED -> deadlineMessage("중간 보고서", schedule);
            case FINAL_THESIS_NOT_SUBMITTED -> deadlineMessage("최종 보고서", schedule);
            case CERTIFICATE_NOT_SUBMITTED -> deadlineMessage("자격증", schedule);

            case GRADUATION_TYPE_NOT_SUBMITTED ->
                    "졸업 유형(논문/자격증)을 먼저 선택해주세요.";
            case PROFESSOR_NOT_ASSIGNED ->
                    "지도교수 배정을 완료해야 다음 절차를 진행할 수 있어요.";
            case GRADUATION_REQUIREMENTS_MET ->
                    "현재 기준으로 필요한 졸업 요건이 모두 완료되었어요.";
        };

        return MyGraduationUserResponse.builder()
                .title(title)
                .description(description)
                .build();
    }

    /**
     * 서비스에서 schedule 조회할 때 쓰라고 status -> SubmissionType 매핑도 제공
     */
    public static SubmissionType requiredSubmissionType(GraduationUser graduationUser) {
        GraduationUserStatus status = determineStatus(graduationUser);

        return switch (status) {
            case MID_THESIS_NOT_SUBMITTED -> SubmissionType.MIDTHESIS;
            case FINAL_THESIS_NOT_SUBMITTED -> SubmissionType.FINALTHESIS;
            case CERTIFICATE_NOT_SUBMITTED -> SubmissionType.CERTIFICATE;
            default -> null; // schedule 필요 없는 상태들
        };
    }

    private static String deadlineMessage(String target, Schedule schedule) {
        LocalDateTime endDate = (schedule == null) ? null : schedule.getEndDate();
        String formatted = (endDate == null) ? "미정" : endDate.format(KOREAN_DATE);
        return target + " 마감 기한은 " + formatted + "이에요.";
    }

    private static GraduationUserStatus determineStatus(GraduationUser graduationUser) {
        if (graduationUser.getGraduationType() == null) {
            return GraduationUserStatus.GRADUATION_TYPE_NOT_SUBMITTED;
        }

        if (graduationUser.getAdvisorProfessor() == null) {
            return GraduationUserStatus.PROFESSOR_NOT_ASSIGNED;
        }

        if (graduationUser.getGraduationType() == GraduationType.THESIS) {
            if (graduationUser.getMidThesisId() == null) {
                return GraduationUserStatus.MID_THESIS_NOT_SUBMITTED;
            }
            if (graduationUser.getFinalThesisId() == null) {
                return GraduationUserStatus.FINAL_THESIS_NOT_SUBMITTED;
            }
        } else if (graduationUser.getGraduationType() == GraduationType.CERTIFICATE) {
            if (graduationUser.getCertificateId() == null) {
                return GraduationUserStatus.CERTIFICATE_NOT_SUBMITTED;
            }
        }

        return GraduationUserStatus.GRADUATION_REQUIREMENTS_MET;
    }
}

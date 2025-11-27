package kgu.developers.api.graduationUser.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import lombok.Builder;

@Builder
public record MyGraduationUserResponse(
    @Schema(description = "현재 상태", example = "GRADUATION_TYPE_NOT_SUBMITTED")
    GraduationUserStatus status
) {
    public static MyGraduationUserResponse from(GraduationUser graduationUser) {
        return MyGraduationUserResponse.builder()
            .status(determineStatus(graduationUser))
            .build();
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
        }
        else if (graduationUser.getGraduationType() == GraduationType.CERTIFICATE) {
            if (graduationUser.getCertificateId() == null) {
                return GraduationUserStatus.CERTIFICATE_NOT_SUBMITTED;
            }
        }

        return GraduationUserStatus.GRADUATION_REQUIREMENTS_MET;
    }
}

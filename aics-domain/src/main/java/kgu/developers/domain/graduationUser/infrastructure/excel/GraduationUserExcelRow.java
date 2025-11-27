package kgu.developers.domain.graduationUser.infrastructure.excel;

import kgu.developers.domain.graduationUser.domain.GraduationUser;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record GraduationUserExcelRow(
    String userId,
    String name,
    String department,
    LocalDate graduationDate,
    String graduationType,
    String advisorProfessor,
    String currentStage,
    String approvalStatus
) {
    public static GraduationUserExcelRow from(GraduationUser graduationUser, String stage, String status) {
        return GraduationUserExcelRow.builder()
            .userId(graduationUser.getUserId())
            .name(graduationUser.getName())
            .department(graduationUser.getDepartment())
            .graduationDate(graduationUser.getGraduationDate())
            .graduationType(graduationUser.getGraduationType().getDescription())
            .advisorProfessor(graduationUser.getAdvisorProfessor())
            .currentStage(stage)
            .approvalStatus(status)
            .build();
    }
}

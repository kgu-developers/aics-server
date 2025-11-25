package kgu.developers.admin.graduationUser.presentation.dto;

import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
public record GraduationUserSummaryDto(
        Long id,
        String studentId,
        String name,
        LocalDate graduationDate,
        GraduationType graduationType,
        String status
) {
    public static GraduationUserSummaryDto from(GraduationUser graduationUser, String status) {
        return GraduationUserSummaryDto.builder()
                .id(graduationUser.getId())
                .studentId(graduationUser.getUserId())
                .name(graduationUser.getName())
                .graduationDate(graduationUser.getGraduationDate())
                .graduationType(graduationUser.getGraduationType())
                .status(status)
                .build();
    }
}

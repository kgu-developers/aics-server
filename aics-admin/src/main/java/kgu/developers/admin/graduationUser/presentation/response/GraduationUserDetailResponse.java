package kgu.developers.admin.graduationUser.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.format.DateTimeFormatter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record GraduationUserDetailResponse(
    @Schema(description = "졸업 대상자 id", example = "1", requiredMode = REQUIRED)
    Long graduationUserId,

    @Schema(description = "이름", example = "박민준", requiredMode = REQUIRED)
    String name,

    @Schema(description = "학번(교번)", example = "202412345", requiredMode = REQUIRED)
    String studentId,

    @Schema(description = "졸업 날짜", example = "2028-08", requiredMode = REQUIRED)
    @DateTimeFormat(pattern = "yyyy-MM")
    String graduationDate,

    @Schema(description = "지도교수", example = "김교수", requiredMode = REQUIRED)
    String advisor,

    @Schema(description = "학과", example = "컴퓨터공학과", requiredMode = REQUIRED)
    String major,

    @Schema(description = "캡스톤 이수 여부", example = "true", requiredMode = REQUIRED)
    Boolean capstoneCompletion
) {
    public static GraduationUserDetailResponse from(
        GraduationUser graduationUser
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        return GraduationUserDetailResponse.builder()
            .graduationUserId(graduationUser.getId())
            .name(graduationUser.getName())
            .studentId(graduationUser.getUserId())
            .graduationDate(graduationUser.getGraduationDate().format(formatter))
            .advisor(graduationUser.getAdvisorProfessor())
            .major(graduationUser.getDepartment())
            .capstoneCompletion(graduationUser.getCapstoneCompletion())
            .build();
    }
}

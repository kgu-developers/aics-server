package kgu.developers.admin.graduationUser.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.format.DateTimeFormatter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record GraduationUserSummaryResponse(
    @Schema(description = "졸업 대상자 id", example = "1", requiredMode = REQUIRED)
    Long id,

    @Schema(description = "졸업 대상자 학번", example = "202211456", requiredMode = REQUIRED)
    String studentId,

    @Schema(description = "졸업 대상자 이름", example = "홍길동", requiredMode = REQUIRED)
    String name,

    @Schema(description = "졸업 날짜", example = "2028-08-01", requiredMode = REQUIRED)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    String graduationDate,

    @Schema(description = "졸업 유형", example = "자격증", requiredMode = REQUIRED)
    String graduationType,

    @Schema(description = "상태", example = "미제출", requiredMode = REQUIRED)
    String status
) {
    public static GraduationUserSummaryResponse from(GraduationUser graduationUser) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return GraduationUserSummaryResponse.builder()
            .id(graduationUser.getId())
            .studentId(graduationUser.getUserId())
            .name(graduationUser.getName())
            .graduationDate(graduationUser.getGraduationDate().format(formatter))
            .graduationType(graduationUser.getGraduationType() != null ? graduationUser.getGraduationType().getDescription() : "미정")
            .status("") //TODO: Thesis, Certificate관련 로직이 추가되면 변경하여야 합니다.
            .build();
    }
}

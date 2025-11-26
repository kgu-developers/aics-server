package kgu.developers.admin.graduationUser.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record GraduationUserCreateRequest(
    @Schema(description = "졸업 대상자 학번", example = "202211461", requiredMode = REQUIRED)
    @NotBlank
    @Size(min = 9, max = 9)
    String studentId,

    @Schema(description = "졸업 대상자 이름", example = "홍길동", requiredMode = REQUIRED)
    @NotBlank
    @Size(max = 10)
    String name,

    @Schema(description = "담당교수 이름", example = "김교수", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(max = 10)
    String advisorProfessor,

    @Schema(description = "캡스톤 이수 여부", example = "true", requiredMode = REQUIRED)
    @NotNull
    Boolean capstoneCompletion,

    @Schema(description = "전공", example = "컴퓨터공학전공", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 10)
    String department,

    @Schema(description = "졸업일", example = "2028-02-01", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    LocalDate graduationDate
) {
}

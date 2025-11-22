package kgu.developers.api.graduationUser.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record GraduationUserEmailUpdateRequest(
    @Schema(description = "이메일", example = "qkralswnsWkd@kyonggi.ac.kr", requiredMode = REQUIRED)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@(kyonggi|kgu)\\.ac\\.kr$", message = "학교 이메일 형식으로 입력해주세요.")
    @NotBlank(message = "이메일은 필수 값입니다.")
    String email
) {
}

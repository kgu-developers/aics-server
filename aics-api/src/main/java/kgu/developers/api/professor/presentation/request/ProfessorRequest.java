package kgu.developers.api.professor.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import kgu.developers.domain.professor.domain.Role;

public record ProfessorRequest(
	@Schema(description = "교수 이름", example = "이은정", requiredMode = REQUIRED)
	@NotNull
	String name,

	@Schema(description = "직위", example = "ASSISTANT", requiredMode = REQUIRED)
	@NotNull
	Role role,

	@Schema(description = "연락처", example = "031-249-9671", requiredMode = REQUIRED)
	@Pattern(regexp = "^\\d{2,4}-\\d{3,4}-\\d{4}$", message = "유효한 전화번호 형식이 아닙니다.")
	@NotNull
	String contact,

	@Schema(description = "이메일", example = "ejlee@kyonggi.ac.kr", requiredMode = REQUIRED)
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@(kyonggi|kgu)\\.ac\\.kr$", message = "학교 이메일 형식으로 입력해주세요.")
	@NotNull
	String email
) {
}



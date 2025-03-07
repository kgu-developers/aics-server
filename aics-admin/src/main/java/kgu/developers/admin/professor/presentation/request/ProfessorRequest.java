package kgu.developers.admin.professor.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record ProfessorRequest(
	@Schema(description = "교수 이름", example = "이은정", requiredMode = REQUIRED)
	@NotNull
	String name,

	@Schema(description = "직위", example = "조교수", requiredMode = REQUIRED)
	@NotNull
	String role,

	@Schema(description = "연락처", example = "031-249-9671", requiredMode = REQUIRED)
	@Pattern(regexp = "^\\d{2,4}-\\d{3,4}-\\d{4}$", message = "유효한 전화번호 형식이 아닙니다.")
	@NotNull
	String contact,

	@Schema(description = "이메일", example = "ejlee@kyonggi.ac.kr", requiredMode = REQUIRED)
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@(kyonggi|kgu)\\.ac\\.kr$", message = "학교 이메일 형식으로 입력해주세요.")
	@NotNull
	String email,

	@Schema(description = "이미지 URL", example = "https://image.com/professor/profile/image", requiredMode = REQUIRED)
	@NotNull
	@Pattern(regexp = "^https?://.*$", message = "올바른 URL 형식이 아닙니다.")
	String img,

	@Schema(description = "연구실 위치", example = "8213호", requiredMode = REQUIRED)
	@NotNull
	String officeLoc
) {
}



package kgu.developers.api.professor.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ProfessorRequest(
	@Schema(description = "교수 이름", example = "홍길동", requiredMode = REQUIRED)
	@NotNull
	String name,

	@Schema(description = "사무실 위치", example = "8000호", requiredMode = REQUIRED)
	@NotNull
	String officeLoc,

	@Schema(description = "연락처", example = "010-0000-0001", requiredMode = REQUIRED)
	@Pattern(regexp = "^\\d{2,4}-\\d{3,4}-\\d{4}$", message = "유효한 전화번호 형식이 아닙니다.")
	@NotNull
	String contact,

	@Schema(description = "이메일", example = "prof@kyonggi.ac.kr", requiredMode = REQUIRED)
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@(kyonggi|kgu)\\.ac\\.kr$", message = "학교 이메일 형식으로 입력해주세요.")
	@NotNull
	String email,

	@Schema(description = "담당과목", example = "컴퓨터공학개론", requiredMode = REQUIRED)
	@NotNull
	String course
) {
}



package kgu.developers.apis.api.user.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import kgu.developers.core.domain.user.domain.Major;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record UserCreateRequest(

	@Schema(description = "학번", example = "202412345", requiredMode = REQUIRED)
	@Pattern(regexp = "\\d{9}", message = "학번은 9자리 숫자로 입력해야 합니다.")
	@NotNull
	String userId,

	@Schema(description = "비밀번호", example = "password1234", requiredMode = REQUIRED)
	@NotNull
	String password,

	@Schema(description = "이름", example = "박민준", requiredMode = REQUIRED)
	@NotNull
	String name,

	@Schema(description = "이메일", example = "qkralswnsWkd@kyonggi.ac.kr", requiredMode = REQUIRED)
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "유효한 이메일 형식이 아닙니다.")
	String email,

	@Schema(description = "전화번호", example = "010-1234-5678", requiredMode = REQUIRED)
	@Pattern(regexp = "^\\d{2,4}-\\d{3,4}-\\d{4}$", message = "유효한 전화번호 형식이 아닙니다.")
	String phoneNumber,

	@Schema(description = "전공 이름", example = "컴퓨터공학부", requiredMode = REQUIRED)
	@NotNull
	Major major
) {
}

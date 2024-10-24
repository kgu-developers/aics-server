package kgu.developers.apis.api.user.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import kgu.developers.core.domain.user.domain.Major;

public record UserCreateRequest(

	@Schema(description = "학번", example = "202412345", requiredMode = REQUIRED)
	@Pattern(regexp = "\\d{9}", message = "학번은 9자리 숫자로 입력해야 합니다.")
	@NotNull
	String userId,

	@Schema(description = "비밀번호", example = "password1234", requiredMode = REQUIRED)
	@Pattern(
		regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,15}$",
		message = "비밀번호는 영문, 숫자, 특수문자를 포함하여 8~15자리여야 합니다."
	)
	@NotNull
	String password,

	@Schema(description = "이름", example = "박민준", requiredMode = REQUIRED)
	@NotNull
	String name,

	@Schema(description = "이메일", example = "qkralswnsWkd@kyonggi.ac.kr", requiredMode = REQUIRED)
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@(kyonggi|kgu)\\.ac\\.kr$", message = "학교 이메일 형식으로 입력해주세요.")
	String email,

	@Schema(description = "전화번호", example = "010-1234-5678", requiredMode = REQUIRED)
	@Pattern(regexp = "^\\d{2,4}-\\d{3,4}-\\d{4}$", message = "유효한 전화번호 형식이 아닙니다.")
	String phoneNumber,

	@Schema(description = "전공 이름", example = "컴퓨터공학부", requiredMode = REQUIRED)
	@NotNull
	Major major
) {
}

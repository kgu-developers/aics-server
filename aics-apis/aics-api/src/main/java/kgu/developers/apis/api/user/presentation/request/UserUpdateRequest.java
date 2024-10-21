package kgu.developers.apis.api.user.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserUpdateRequest(
	@Schema(description = "전화번호", example = "010-1234-5678", requiredMode = REQUIRED)
	@Pattern(regexp = "^\\d+-\\d+-\\d", message = "유효한 전화번호 형식이 아닙니다.")
	String phoneNumber,

	@Schema(description = "생년월일", example = "19991022", requiredMode = REQUIRED)
	@Pattern(regexp = "\\d{8}", message = "생년월일은 8자리 숫자로 입력해야 합니다.")
	@NotNull
	String birth,

	@Schema(description = "이메일", example = "qkralswnsWkd@kyonggi.ac.kr", requiredMode = REQUIRED)
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "유효한 이메일 형식이 아닙니다.")
	String email
) {
}

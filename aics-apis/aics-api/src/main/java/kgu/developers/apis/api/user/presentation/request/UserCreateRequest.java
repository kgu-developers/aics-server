package kgu.developers.apis.api.user.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import kgu.developers.core.domain.user.domain.Gender;
import kgu.developers.core.domain.user.domain.Grade;

public record UserCreateRequest(

	@Schema(description = "학번", example = "202412345", requiredMode = REQUIRED)
	@Pattern(regexp = "\\d{9}", message = "학번은 9자리 숫자로 입력해야 합니다.")
	@NotNull
	String personalId,

	@Schema(description = "비밀번호", example = "password1234", requiredMode = REQUIRED)
	@NotNull
	String password,

	@Schema(description = "이름", example = "박민준", requiredMode = REQUIRED)
	@NotNull
	String name,

	@Schema(description = "생년월일", example = "19991022", requiredMode = REQUIRED)
	@Pattern(regexp = "\\d{8}", message = "생년월일은 8자리 숫자로 입력해야 합니다.")
	@NotNull
	String birth,

	@Schema(description = "성별", example = "MEN", requiredMode = REQUIRED)
	@NotNull
	Gender gender,

	@Schema(description = "학년", example = "SENIOR", requiredMode = REQUIRED)
	@NotNull
	Grade grade,

	@Schema(description = "전공 이름", example = "컴퓨터공학부", requiredMode = REQUIRED)
	@NotNull
	String majorName,

	@Schema(description = "이메일", example = "example111@gmail.com", requiredMode = REQUIRED)
	@NotNull
	String email,

	@Schema(description = "전화번호", example = "010-1234-5678", requiredMode = REQUIRED)
	@NotNull
	String phone
) {
}

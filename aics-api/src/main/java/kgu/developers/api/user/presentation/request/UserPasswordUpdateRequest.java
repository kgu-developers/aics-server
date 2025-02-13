package kgu.developers.api.user.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record UserPasswordUpdateRequest(
	@Schema(description = "원래 비밀번호", example = "password1234!", requiredMode = REQUIRED)
	@Pattern(
		regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,15}$",
		message = "비밀번호는 영문, 숫자, 특수문자를 포함하여 8~15자리여야 합니다."
	)
	@NotNull
	String originalPassword,

	@Schema(description = "새로운 비밀번호", example = "newpass1234!", requiredMode = REQUIRED)
	@Pattern(
		regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,15}$",
		message = "비밀번호는 영문, 숫자, 특수문자를 포함하여 8~15자리여야 합니다."
	)
	@NotNull
	String newPassword
) {
}

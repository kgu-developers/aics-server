package kgu.developers.admin.user.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record UserKickOutRequest(
	@Schema(description = "학번", example = "202412345", requiredMode = REQUIRED)
	@Pattern(regexp = "\\d{9}", message = "학번은 9자리 숫자로 입력해야 합니다.")
	@NotNull
	String userId
) {
}

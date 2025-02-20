package kgu.developers.admin.user.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record UserKickOutListRequest(
	@Schema(description = "학번 리스트", example = "[\"202412347\", \"202412349\"]", requiredMode = REQUIRED)
	@NotEmpty(message = "학번 리스트는 비어 있을 수 없습니다.")
	List<@Pattern(regexp = "\\d{9}", message = "학번은 9자리 숫자로 입력해야 합니다.") String> userIds
) {
}

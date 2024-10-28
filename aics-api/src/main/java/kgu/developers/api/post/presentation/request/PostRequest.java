package kgu.developers.api.post.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(
	@Schema(description = "게시물 제목", example = "박민준은 살아있다", requiredMode = REQUIRED)
	@NotBlank
	@Size(max = 100, message = "제목은 100자 이내여야 합니다.")
	String title,

	@Schema(description = "게시물 내용", example = "살아 숨셔", requiredMode = REQUIRED)
	@NotBlank
	String content
) {
}

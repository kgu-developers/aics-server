package kgu.developers.apis.api.post.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kgu.developers.core.domain.post.Category;

public record PostCreateRequest(
	@Schema(description = "게시물 제목", example = "박민준은 살아있다", requiredMode = REQUIRED)
	@NotBlank(message = "제목은 필수입니다.")
	@Size(max = 100, message = "제목은 100자 이내여야 합니다.")
	String title,

	@Schema(description = "게시물 내용", example = "살아 숨셔", requiredMode = REQUIRED)
	@NotBlank(message = "내용은 필수입니다.")
	String content,

	/*
	@Schema(description = "게시물 카테고리", example = "DEPT_INFO", requiredMode = REQUIRED)
	@NotNull(message = "카테고리는 필수입니다.")
	*/
	Category category
) {
}

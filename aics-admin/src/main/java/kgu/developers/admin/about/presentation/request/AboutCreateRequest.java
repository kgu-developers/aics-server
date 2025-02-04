package kgu.developers.admin.about.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import kgu.developers.domain.about.domain.Category;
import lombok.Builder;

@Builder
public record AboutCreateRequest(
	@Schema(description = "카테고리", example = "DEPT_INTRO", requiredMode = REQUIRED)
	@NotNull
	Category category,

	@Schema(description = "페이지 내용(JSON 형식)", example = "{key:value}", requiredMode = REQUIRED)
	@NotNull
	String content
) {
}

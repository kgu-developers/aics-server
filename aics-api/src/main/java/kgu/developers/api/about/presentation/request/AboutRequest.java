package kgu.developers.api.about.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import kgu.developers.domain.about.domain.MainCategory;
import kgu.developers.domain.about.domain.SubCategory;
import lombok.Builder;

@Builder
public record AboutRequest(
	@Schema(description = "메인 카테고리", example = "EDU_ACTIVITIES", requiredMode = REQUIRED)
	@NotNull
	MainCategory main,

	@Schema(description = "보조 카테고리", example = "CURRICULUM", requiredMode = REQUIRED)
	@NotNull
	SubCategory sub,

	@Schema(description = "세부 카테고리", example = "2019", requiredMode = NOT_REQUIRED)
	String detail,

	@Schema(description = "페이지 내용(JSON 형식)", example = "{key:value}", requiredMode = REQUIRED)
	@NotNull
	String content
) {
}

package kgu.developers.api.about.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.Category;
import lombok.Builder;

@Builder
public record AboutResponse(
	@Schema(description = "소개글 id", example = "1", requiredMode = REQUIRED)
	Long id,

	@Schema(description = "카테고리", example = "학부 소개", requiredMode = REQUIRED)
	String category,

	@Schema(description = "카테고리 설명", example = "경기대학교 AI컴퓨터공학부를 소개해요.", requiredMode = REQUIRED)
	String description,

	@Schema(description = "페이지 내용(JSON 형식)", example = "{key:value}", requiredMode = Schema.RequiredMode.REQUIRED)
	String content
) {
	public static AboutResponse from(About about) {
		return AboutResponse.builder()
			.id(about.getId())
			.category(about.getCategory().getDescription())
			.description(about.getDescription())
			.content(about.getContent())
			.build();
	}
}

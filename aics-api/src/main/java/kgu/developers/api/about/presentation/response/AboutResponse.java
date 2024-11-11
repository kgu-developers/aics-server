package kgu.developers.api.about.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.about.domain.About;
import lombok.Builder;

@Builder
public record AboutResponse(
	@Schema(description = "소개글 id", example = "1", requiredMode = REQUIRED)
	Long id,

	@Schema(description = "페이지 내용(JSON 형식)", example = "{key:value}", requiredMode = Schema.RequiredMode.REQUIRED)
	String content
) {
	public static AboutResponse from(About about) {
		return AboutResponse.builder()
			.id(about.getId())
			.content(about.getContent())
			.build();
	}
}

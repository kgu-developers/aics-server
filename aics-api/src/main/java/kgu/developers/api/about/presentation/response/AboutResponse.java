package kgu.developers.api.about.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.about.domain.About;
import lombok.Builder;

@Builder
public record AboutResponse(
	@Schema(description = "페이지 내용", example = "학과 소개 내용입니다.", requiredMode = REQUIRED)
	String content
) {
	public static AboutResponse from(About about) {
		return AboutResponse.builder()
			.content(about.getContent())
			.build();
	}
}

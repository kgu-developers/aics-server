package kgu.developers.admin.about.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AboutUpdateRequest(
	@Schema(description = "카테고리 설명", example = "경기대학교 AI컴퓨터공학부를 소개해요.", requiredMode = REQUIRED)
	@NotNull
	String description,

	@Schema(description = "페이지 내용(JSON 형식)", example = "{key:value}", requiredMode = REQUIRED)
	@NotNull
	String content
) {
}

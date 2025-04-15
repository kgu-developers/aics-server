package kgu.developers.admin.about.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AboutUpdateRequest(
	@Schema(description = "페이지 내용", example = "학과 소개 내용입니다.", requiredMode = REQUIRED)
	@NotNull
	String content
) {
}

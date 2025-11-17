package kgu.developers.api.thesis.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ThesisPersistResponse(
	@Schema(description = "졸업 논문 객체 id", example = "1", requiredMode = REQUIRED)
	Long id
) {
	public static ThesisPersistResponse of(Long id) {
		return ThesisPersistResponse.builder().id(id).build();
	}
}

package kgu.developers.api.about.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record AboutPersistResponse(
	@Schema(description = "소개글 id", example = "3", requiredMode = REQUIRED)
	Long id
) {
	public static AboutPersistResponse of(Long id) {
		return AboutPersistResponse.builder()
			.id(id)
			.build();
	}
}

package kgu.developers.admin.club.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ClubPersistResponse(
	@Schema(description = "동아리 id", example = "1", requiredMode = REQUIRED)
	Long id
) {
	public static ClubPersistResponse of(Long id) {
		return ClubPersistResponse.builder()
			.id(id)
			.build();
	}
}

package kgu.developers.api.lab.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record LabPersistResponse(
	@Schema(description = "연구실 id", example = "99", requiredMode = REQUIRED)
	Long id
) {
	public static LabPersistResponse of(Long id) {
		return LabPersistResponse.builder()
			.id(id)
			.build();
	}
}

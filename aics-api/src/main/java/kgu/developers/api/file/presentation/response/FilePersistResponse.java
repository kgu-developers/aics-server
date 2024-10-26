package kgu.developers.api.file.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record FilePersistResponse(
	@Schema(description = "파일ID", example = "1", requiredMode = REQUIRED)
	String id
) {
	public static FilePersistResponse of(String id) {
		return FilePersistResponse.builder().id(id).build();
	}
}

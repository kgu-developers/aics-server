package kgu.developers.apis.api.file.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record FilePersistResponse(
	@Schema(description = "파일ID", example = "1", requiredMode = REQUIRED)
	String id
) {
	public static FilePersistResponse of(String id) {
		return new FilePersistResponse(id);
	}
}

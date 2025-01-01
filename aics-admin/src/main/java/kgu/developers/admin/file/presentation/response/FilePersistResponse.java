package kgu.developers.admin.file.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record FilePersistResponse(
	@Schema(description = "파일 id", example = "1", requiredMode = REQUIRED)
	String id
) {
	public static FilePersistResponse of(String id) {
		return FilePersistResponse.builder().id(id).build();
	}
}

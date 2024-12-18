package kgu.developers.api.file.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record FilePathResponse(
	@Schema(description = "파일 경로", example = "/cloud/file/3/2025-curriculum", requiredMode = REQUIRED)
	String physicalPath
) {
	public static FilePathResponse of(String decryptedPath) {
		return FilePathResponse.builder()
			.physicalPath(decryptedPath)
			.build();
	}
}
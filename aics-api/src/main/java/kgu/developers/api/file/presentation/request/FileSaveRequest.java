package kgu.developers.api.file.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kgu.developers.domain.file.domain.FileDomain;

public record FileSaveRequest(
	@NotNull
	@Schema(description = "파일 도메인", example = "ABOUT", requiredMode = REQUIRED)
	FileDomain fileDomain,

	@NotNull @Positive
	@Schema(description = "디렉토리 id", example = "3", requiredMode = REQUIRED)
	Long directoryId
) {

}

package kgu.developers.api.file.presentation.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FileSaveRequest(
	@NotNull @Positive
	@Schema(description = "디렉토리 id", example = "3", requiredMode = REQUIRED)
	Long directoryId
) {
}

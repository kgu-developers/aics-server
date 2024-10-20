package kgu.developers.apis.api.major.presentation.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@JsonNaming(value = SnakeCaseStrategy.class)
public record MajorCreateRequest(
    @Schema(description = "학과명", example = "AI컴퓨터공학과", requiredMode = REQUIRED)
    @NonNull
    String MajorName
) {
}

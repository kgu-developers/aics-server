package kgu.developers.apis.api.major.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record MajorCreateRequest(
    @Schema(description = "학과명", example = "AI컴퓨터공학과", requiredMode = REQUIRED)
    @NotNull
    @Size(min = 1, max = 10)
    String name
) {
}

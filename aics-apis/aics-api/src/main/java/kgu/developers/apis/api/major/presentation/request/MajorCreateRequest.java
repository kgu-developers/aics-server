package kgu.developers.apis.api.major.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record MajorCreateRequest(
    @Schema(description = "학과명", example = "AI컴퓨터공학과", requiredMode = REQUIRED)
    @NonNull
    String name
) {
}

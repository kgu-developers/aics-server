package kgu.developers.apis.api.major.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record MajorPersistResponse(
    @Schema(description = "전공식별자", example = "1", requiredMode = REQUIRED)
    Long id,

    @Schema(description = "전공명", example = "AI컴퓨터공학부", requiredMode = REQUIRED)
    String name
) {
    public static MajorPersistResponse of(Long id, String name) {
        return new MajorPersistResponse(id, name);
    }
}

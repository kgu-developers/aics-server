package kgu.developers.apis.api.major.presentation.response;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.core.domain.major.domain.Major;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@JsonNaming()
public record MajorPersistResponse(
    @Schema(description = "전공식별자", example = "1", requiredMode = REQUIRED)
    Long majorId,

    @Schema(description = "전공명", example = "AI컴퓨터공학부", requiredMode = REQUIRED)
    String majorName
) {
    public static MajorPersistResponse from(Major major) {
        return new MajorPersistResponse(major.getId(), major.getName());
    }
}

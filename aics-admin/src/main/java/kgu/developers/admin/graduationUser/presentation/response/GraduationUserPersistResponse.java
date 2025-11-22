package kgu.developers.admin.graduationUser.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record GraduationUserPersistResponse(
    @Schema(description = "졸업 대상자 id", example = "1", requiredMode = REQUIRED)
    Long id
) {
    public static GraduationUserPersistResponse of(Long id) {
        return GraduationUserPersistResponse.builder()
            .id(id)
            .build();
    }
}

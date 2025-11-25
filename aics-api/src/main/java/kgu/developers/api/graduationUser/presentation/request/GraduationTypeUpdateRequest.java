package kgu.developers.api.graduationUser.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record GraduationTypeUpdateRequest(
    @Schema(description = "졸업 방식", example = "THESIS", requiredMode = REQUIRED)
    GraduationType graduationType
) {
}

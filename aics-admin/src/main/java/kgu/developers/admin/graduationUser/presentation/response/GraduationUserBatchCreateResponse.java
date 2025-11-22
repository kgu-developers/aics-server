package kgu.developers.admin.graduationUser.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record GraduationUserBatchCreateResponse(
    @Schema(description = "생성에 성공한 졸업 대상자 ID 목록", example = "[1, 2, 3, 4, 5]", requiredMode = REQUIRED)
    List<Long> createdIds
) {
    public static GraduationUserBatchCreateResponse from(List<Long> createdIds) {
        return GraduationUserBatchCreateResponse.builder()
            .createdIds(createdIds)
            .build();
    }
}

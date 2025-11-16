package kgu.developers.admin.graduationUser.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record GraduationUserBatchDeleteResponse(
    @Schema(description = "삭제를 성공한 졸업 대상자 ID 목록", example = "[1, 2, 3, 4, 5]", requiredMode = REQUIRED)
    List<Long> deletedIds
) {
    public static GraduationUserBatchDeleteResponse from(List<Long> deletedIds) {
        return GraduationUserBatchDeleteResponse.builder()
            .deletedIds(deletedIds)
            .build();
    }
}

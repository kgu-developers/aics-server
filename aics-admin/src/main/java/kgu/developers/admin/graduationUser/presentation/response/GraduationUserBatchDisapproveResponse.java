package kgu.developers.admin.graduationUser.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record GraduationUserBatchDisapproveResponse(
        @Schema(description = "승인 취소 처리된 졸업 대상자 ID 목록", example = "[1, 2, 3, 4, 5]", requiredMode = REQUIRED)
        List<Long> disapprovedIds
) {
    public static GraduationUserBatchDisapproveResponse from(List<Long> disapprovedUserIds) {
            return GraduationUserBatchDisapproveResponse.builder()
                    .disapprovedIds(disapprovedUserIds)
                    .build();
    }
}

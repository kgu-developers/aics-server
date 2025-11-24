package kgu.developers.admin.graduationUser.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record GraduationUserBatchApproveResponse(
        @Schema(description = "승인에 성공한 졸업 대상자 ID 목록", example = "[1, 2, 3, 4, 5]", requiredMode = REQUIRED)
        List<Long> approvedIds
) {
    public static GraduationUserBatchApproveResponse from(List<Long> approvedUserIds) {
            return GraduationUserBatchApproveResponse.builder()
                    .approvedIds(approvedUserIds)
                    .build();
    }
}

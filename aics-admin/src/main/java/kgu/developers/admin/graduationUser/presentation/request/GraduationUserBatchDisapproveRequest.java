package kgu.developers.admin.graduationUser.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.util.List;

@Builder
public record GraduationUserBatchDisapproveRequest(
        @Schema(description = "승인 취소할 졸업 대상자 ID 목록", example = "[1, 2, 3]")
        @NotEmpty(message = "승인 취소할 대상자를 선택해주세요.")
        List<@Positive(message = "잘못된 ID 형식입니다.") Long> ids
) {
}

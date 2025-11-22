package kgu.developers.admin.graduationUser.presentation.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.util.List;

@Builder
public record GraduationUserBatchDeleteRequest(
    @Schema(description = "삭제할 졸업 대상자 ID 목록", example = "[1, 2, 3]")
    @NotEmpty(message = "삭제할 대상자를 최소 1명 이상 선택해야 합니다.")
    List<@Positive(message = "ID는 양수여야 합니다.") Long> ids
) {

}

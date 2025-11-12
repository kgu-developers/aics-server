package kgu.developers.admin.graduationUser.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.common.domain.BaseRole;
import kgu.developers.domain.user.domain.Major;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record GraduationUserDetailResponse(
    @Schema(description = "이름", example = "박민준", requiredMode = REQUIRED)
    String name,

    @Schema(description = "전화번호", example = "010-1234-5678", requiredMode = REQUIRED)
    String phone,

    @Schema(description = "이메일", example = "qkralswnsWkd@kyonggi.ac.kr", requiredMode = REQUIRED)
    String email,

    @Schema(description = "구분", example = "학부생", requiredMode = REQUIRED)
    BaseRole role,

    @Schema(description = "학과", example = "컴퓨터공학과", requiredMode = REQUIRED)
    Major major,

    @Schema(description = "학번(교번)", example = "202412345", requiredMode = REQUIRED)
    String id
) {

}

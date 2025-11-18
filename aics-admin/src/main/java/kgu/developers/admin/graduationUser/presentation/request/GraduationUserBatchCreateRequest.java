package kgu.developers.admin.graduationUser.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record GraduationUserBatchCreateRequest(
    @Schema(description = "졸업 대상자 생성 리스트",
        example = "[{"
            + "\"studentId\": \"202211461\", "
            + "\"name\": \"홍길동\", "
            + "\"advisorProfessor\": \"김교수\", "
            + "\"capstoneCompletion\": \"true\", "
            + "\"department\": \"컴퓨터공학전공\", "
            + "\"graduationDate\": \"2028-02-01\"}]",
        requiredMode = REQUIRED)
    @NotEmpty
    List<GraduationUserCreateRequest> graduationUsers
) {
}

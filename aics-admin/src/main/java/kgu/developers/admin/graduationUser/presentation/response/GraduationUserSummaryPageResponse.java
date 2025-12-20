package kgu.developers.admin.graduationUser.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.common.response.PageableResponse;
import lombok.Builder;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record GraduationUserSummaryPageResponse<T>(
        @Schema(description = "졸업 대상자 페이지 리스트",
                example = "[{"
                        + "\"id\": 3, "
                        + "\"studentId\": \"202211456\", "
                        + "\"name\": \"홍길동\", "
                        + "\"graduationDate\": \"2028-08\", "
                        + "\"graduationType\": \"자격증\", "
                        + "\"status\": {"
                        + "\"type\": \"CERTIFICATE\", "
                        + "\"submitted\": true, "
                        + "\"approval\": false"
                        + "}}, {"
                        + "\"id\": 4, "
                        + "\"studentId\": \"202211457\", "
                        + "\"name\": \"김철수\", "
                        + "\"graduationDate\": \"2028-08\", "
                        + "\"graduationType\": \"논문\", "
                        + "\"status\": {"
                        + "\"type\": \"THESIS\", "
                        + "\"midThesis\": {\"submitted\": true, \"approval\": true}, "
                        + "\"finalThesis\": {\"submitted\": false, \"approval\": false}"
                        + "}}]",
                requiredMode = REQUIRED)
        List<GraduationUserSummaryResponse> contents,

    @Schema(description = "페이징 정보", requiredMode = REQUIRED)
    PageableResponse<T> pageable
) {
    public static <T> GraduationUserSummaryPageResponse<T> of(List<GraduationUserSummaryResponse> graduationUsers, PageableResponse<T> pageable) {
        return GraduationUserSummaryPageResponse.<T>builder()
            .contents(graduationUsers)
            .pageable(pageable)
            .build();
    }
}

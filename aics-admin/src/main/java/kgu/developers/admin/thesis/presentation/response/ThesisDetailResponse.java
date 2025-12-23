package kgu.developers.admin.thesis.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.file.application.response.FilePathResponse;
import kgu.developers.domain.thesis.domain.Thesis;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record ThesisDetailResponse(
        @Schema(description = "졸업 논문 객체 id", example = "1", requiredMode = REQUIRED)
        Long id,
        @Schema(description = "졸업 논문 관련 일정 ID",example = "2",requiredMode = REQUIRED)
        Long scheduleId,
        @Schema(description = "승인 여부", example = "false",requiredMode = REQUIRED)
        boolean approval,

        @Schema(description = "첨부 파일 정보",
                example = "{\"id\": 1, "
                        + "\"physicalPath\": \"/files/2025-thesis\"}",
                requiredMode = NOT_REQUIRED)
        FilePathResponse thesisFile

) {
        public static ThesisDetailResponse from(Thesis thesis,String physicalPath) {
                return ThesisDetailResponse.builder()
                        .id(thesis.getId())
                        .scheduleId(thesis.getScheduleId())
                        .approval(thesis.isApproved())
                        .thesisFile(thesis.getThesisFileId() != null
                                ? FilePathResponse.of(thesis.getThesisFileId(),physicalPath): null)
                        .build();
        }
}

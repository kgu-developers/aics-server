package kgu.developers.admin.certificate.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.certificate.domain.Certificate;
import kgu.developers.domain.file.application.response.FilePathResponse;
import lombok.Builder;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record CertificateDetailResponse(
        @Schema(description = "자격증 객체 id", example = "1", requiredMode = REQUIRED)
        Long id,

        @Schema(description = "자격증 관련 일정 ID", example = "4", requiredMode = REQUIRED)
        Long scheduleId,

        @Schema(description = "승인 여부", example = "false",requiredMode = REQUIRED)
        boolean approval,

        @Schema(description = "첨부 파일 정보",
                example = "{\"id\": 1, "
                        + "\"physicalPath\": \"/files/2025-certificate\"}",
                requiredMode = NOT_REQUIRED)
        FilePathResponse certificateFile
        ) {
        public static CertificateDetailResponse from(Certificate certificate,String physicalPath) {
            return CertificateDetailResponse.builder()
                    .id(certificate.getId())
                    .scheduleId(certificate.getScheduleId())
                    .approval(certificate.isApproved())
                    .certificateFile(certificate.getCertificateFileId() != null
                            ? FilePathResponse.of(certificate.getCertificateFileId(),physicalPath)
                            : null
                    )
                    .build();
        }
}

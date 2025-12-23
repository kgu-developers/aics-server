package kgu.developers.admin.certificate.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.admin.certificate.presentation.response.CertificateDetailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Certificate", description = "자격증 관련 API")
public interface CertificateAdminController {
    @Operation(summary = "자격증 개별 조회 API", description = """
        - Description : 자격증 id로 조회합니다.
        - Assignee : 주윤빈
    """)
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = CertificateDetailResponse.class)))
    ResponseEntity<CertificateDetailResponse> getCertificate(
            @Parameter(description = "자격증 id",required = true) @PathVariable Long id
    );
}

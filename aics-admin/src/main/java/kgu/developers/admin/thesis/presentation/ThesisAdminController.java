package kgu.developers.admin.thesis.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.admin.thesis.presentation.response.ThesisDetailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Thesis", description = "졸업 논문 API")
public interface ThesisAdminController {

    @Operation(summary = "졸업 논문 개별 조회 API", description = """
        - Description :논문 id로 조회합니다.
        - Assignee : 주윤빈
    """)
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = ThesisDetailResponse.class)))
    ResponseEntity<ThesisDetailResponse> getThesis(
            @Parameter(description = "논문 id",required = true) @PathVariable Long id
    );
}

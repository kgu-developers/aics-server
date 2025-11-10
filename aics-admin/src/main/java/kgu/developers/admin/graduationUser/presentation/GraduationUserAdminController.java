package kgu.developers.admin.graduationUser.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserCreateRequest;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserPersistResponse;
import kgu.developers.admin.lab.presentation.response.LabPersistResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "GraduationUser", description = "졸업 대상자 관리자 API")
public interface GraduationUserAdminController {

    @Operation(summary = "졸업 대상자 단일 생성 API", description = """
			- Description : 이 API는 단일 졸업 대상자를 생성합니다.
			- Assignee : 장영후
		""")
    @ApiResponse(
        responseCode = "201",
        content = @Content(schema = @Schema(implementation = LabPersistResponse.class)))
    ResponseEntity<GraduationUserPersistResponse> createGraduationUser(
        @Parameter(
            description = "졸업 대상자 단일 생성 request 객체 입니다.",
            required = true
        ) @Valid @RequestBody GraduationUserCreateRequest request
    );
}

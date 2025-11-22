package kgu.developers.admin.graduationUser.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserBatchCreateRequest;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserBatchDeleteRequest;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserCreateRequest;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserBatchCreateResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserBatchDeleteResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserDetailResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserPersistResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserSummaryPageResponse;
import kgu.developers.admin.lab.presentation.response.LabPersistResponse;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "GraduationUser", description = "졸업 대상자 관리자 API")
public interface GraduationUserAdminController {

    @Operation(summary = "졸업 대상자 페이징 조회 API", description = """
		    - Description : 이 API는 졸업 대상자를 페이징 조회하며, 선택적으로 이름과 졸업 방식으로 필터링할 수 있습니다.
		    - Assignee : 장영후
		""")
    @ApiResponse(
        responseCode = "200",
        content = @Content(schema = @Schema(implementation = GraduationUserSummaryPageResponse.class)))
    ResponseEntity<GraduationUserSummaryPageResponse> getGraduationUsersByName(
        @Parameter(
            description = "페이지 인덱스",
            example = "0",
            required = true
        ) @PositiveOrZero @RequestParam(defaultValue = "0") int page,
        @Parameter(
            description = "응답 개수",
            example = "10",
            required = true
        ) @Positive @RequestParam(defaultValue = "10") int size,
        @Parameter(
            description = "유저 이름",
            example = "홍길동"
        ) @RequestParam(required = false) String name,
        @Parameter(
            description = "졸업 방식 카테고리입니다. 미 지정 시 전체 졸업 대상자를 조회합니다.",
            example = "THESIS"
        ) @RequestParam(required = false) GraduationType graduationType
    );

    @Operation(summary = "졸업 대상자 엑셀 파일 다운로드 API", description = """
		    - Description : 이 API는 졸업 대상자를 엑셀 파일 형태로 다운로드 하며, 선택적으로 졸업 방식으로 필터링 할 수 있습니다.
		    - Assignee : 장영후
		""")
    @ApiResponse(
        responseCode = "200",
        content = @Content(mediaType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
    ResponseEntity<Resource> getGraduateUsersExcel(
        @Parameter(
            description = "졸업 방식 카테고리입니다. 미 지정 시 전체 졸업 대상자를 조회합니다.",
            example = "THESIS"
        ) @RequestParam(required = false) GraduationType graduationType
    );

    @Operation(summary = "졸업 대상자 상세 조회 API", description = """
		    - Description : 이 API는 게시글의 상세 정보를 조회합니다.
		    - Assignee : 장영후
		""")
    @ApiResponse(
        responseCode = "200",
        content = @Content(schema = @Schema(implementation = GraduationUserDetailResponse.class)))
    ResponseEntity<GraduationUserDetailResponse> getGraduationUserById(
        @Parameter(
            description = "졸업 대상자 ID는 URL 경로 변수 입니다.",
            example = "1",
            required = true
        ) @Positive @PathVariable Long graduationUserId
    );

    @Operation(summary = "졸업 대상자 단일 생성 API", description = """
			- Description : 이 API는 단일 졸업 대상자를 생성합니다.
			- Assignee : 장영후
		""")
    @ApiResponse(
        responseCode = "201",
        content = @Content(schema = @Schema(implementation = GraduationUserPersistResponse.class)))
    ResponseEntity<GraduationUserPersistResponse> createGraduationUser(
        @Parameter(
            description = "졸업 대상자 단일 생성 request 객체 입니다.",
            required = true
        ) @Valid @RequestBody GraduationUserCreateRequest request
    );

    @Operation(summary = "졸업 대상자 단일 삭제 API", description = """
			- Description : 이 API는 해당 졸업 대상자를 삭제합니다.
			- Assignee : 장영후
		""")
    @ApiResponse(responseCode = "204")
    ResponseEntity<Void> deleteGraduationUser(
        @Parameter(
            description = "졸업 대상자 ID는 URL 경로 변수 입니다.",
            example = "1",
            required = true
        ) @Positive @PathVariable Long id
    );

    @Operation(summary = "졸업 대상자 일괄 생성 API", description = """
			- Description : 이 API는 입력한 여러 졸업 대상자를 일괄 생성합니다.
			- Assignee : 장영후
		""")
    @ApiResponse(responseCode = "200")
    ResponseEntity<GraduationUserBatchCreateResponse> createGraduationUsers(
        @Parameter(
            description = "생성 졸업 대상자 단체 생성 request 객체입니다.",
            required = true
        ) @Valid @RequestBody GraduationUserBatchCreateRequest request
    );

    @Operation(summary = "졸업 대상자 일괄 삭제 API", description = """
			- Description : 이 API는 선택한 여러 졸업 대상자를 일괄 삭제합니다.
			- Assignee : 장영후
		""")
    @ApiResponse(responseCode = "200")
    ResponseEntity<GraduationUserBatchDeleteResponse> deleteGraduationUsers(
        @Parameter(
            description = "삭제할 졸업 대상자 ID 목록",
            required = true
        ) @Valid @RequestBody GraduationUserBatchDeleteRequest request
    );
}

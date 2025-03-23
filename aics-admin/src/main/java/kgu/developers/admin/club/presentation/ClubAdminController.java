package kgu.developers.admin.club.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.admin.club.presentation.request.ClubCreateRequest;
import kgu.developers.admin.club.presentation.request.ClubUpdateRequest;
import kgu.developers.admin.club.presentation.response.ClubPersistResponse;

@Tag(name = "Club", description = "동아리 관리자 API")
public interface ClubAdminController {

	@Operation(summary = "동아리 생성 API", description = """
			- Description : 이 API는 동아리를 생성합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(
		responseCode = "201",
		content = @Content(schema = @Schema(implementation = ClubPersistResponse.class)))
	ResponseEntity<ClubPersistResponse> createClub(
		@Parameter(
			description = "동아리 이미지에 저장할 파일의 ID 입니다.",
			example = "1"
		) @RequestParam(required = false) Long fileId,
		@Parameter(
			description = "동아리 생성 request 객체 입니다.",
			required = true
		) @Valid @RequestBody ClubCreateRequest request
	);

	@Operation(summary = "동아리 수정 API", description = """
			- Description : 이 API는 동아리 정보를 수정합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "204")
	ResponseEntity<Void> updateClub(
		@Parameter(
			description = "동아리 ID는 URL 경로 변수 입니다.",
			example = "1",
			required = true
		) @Positive @PathVariable Long id,
		@Parameter(
			description = "동아리 수정 request 객체 입니다.",
			required = true
		) @Valid @RequestBody ClubUpdateRequest request
	);

	@Operation(summary = "동아리 삭제 API", description = """
			- Description : 이 API는 해당 동아리를 삭제합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "204")
	ResponseEntity<Void> deleteClub(
		@Parameter(
			description = "동아리 ID는 URL 경로 변수 입니다.",
			example = "1",
			required = true
		) @Positive @PathVariable Long id
	);
}

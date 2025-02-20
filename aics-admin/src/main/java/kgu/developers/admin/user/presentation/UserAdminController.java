package kgu.developers.admin.user.presentation;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import kgu.developers.admin.user.presentation.request.UserKickOutListRequest;
import kgu.developers.admin.user.presentation.response.UserDetailPageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "User", description = "회원 관리자 API")
public interface UserAdminController {

	@Operation(summary = "유저 페이징 조회 API", description = """
		    - Description : 이 API는 유저를 페이징 조회하며, 선택적으로 이름으로 필터링할 수 있습니다.
		    - Assignee : 박민준
		""")
	@ApiResponse(
		responseCode = "200",
		content = @Content(schema = @Schema(implementation = UserDetailPageResponse.class)))
	ResponseEntity<UserDetailPageResponse> getUsersByName(
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
			example = "박민준"
		) @RequestParam(required = false) String name
	);


	@Operation(summary = "유저 삭제 API", description = """
		    - Description : 이 API는 유저를 삭제합니다.
		    - Assignee : 이신행
		""")
	@ApiResponse(responseCode = "204")
	ResponseEntity<Void> kickOutUser(
		@Valid @RequestBody UserKickOutListRequest request
	);

	@Hidden
	@Operation(summary = "삭제된 유저 정리 API", description = """
		    - Description : 이 API는 삭제된 지 일정 기간 지난 유저를 영구적으로 삭제 합니다.
		    - Assignee : 이신행
		""")
	ResponseEntity<String> getLastCleanupRunTime();
}

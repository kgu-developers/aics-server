package kgu.developers.admin.user.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import kgu.developers.admin.user.presentation.response.UserDetailPageResponse;

@Tag(name = "User", description = "회원 관리자 API")
public interface UserAdminController {

	@Operation(summary = "유저 페이징 조회 API", description = """
		    - Description : 이 API는 유저를 페이징 조회합니다.
		    - Assignee : 박민준
		""")
	@ApiResponse(
		responseCode = "200",
		content = @Content(schema = @Schema(implementation = UserDetailPageResponse.class)))
	ResponseEntity<UserDetailPageResponse> getUsers(
		@Parameter(
			description = "페이지 인덱스",
			example = "0",
			required = true
		) @PositiveOrZero @RequestParam(defaultValue = "0") int page,
		@Parameter(
			description = "응답 개수",
			example = "10",
			required = true
		) @Positive @RequestParam(defaultValue = "10") int size
	);
}

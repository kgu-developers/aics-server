package kgu.developers.admin.user.presentation;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import kgu.developers.admin.user.application.UserAdminFacade;
import kgu.developers.admin.user.presentation.response.UserDetailPageResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "회원 관리자 API")
public class UserAdminController {
	private final UserAdminFacade userAdminFacade;

	@Operation(summary = "유저 페이징 조회 API", description = """
		    - Description : 이 API는 유저를 페이징 조회합니다.
		    - Assignee : 박민준
		""")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserDetailPageResponse.class)))
	@GetMapping
	public ResponseEntity<UserDetailPageResponse> getUsers(
		@Parameter(description = "페이지 인덱스", example = "0", required = true) @RequestParam(defaultValue = "0") @PositiveOrZero int page,
		@Parameter(description = "응답 개수", example = "10", required = true) @RequestParam(defaultValue = "10") @Positive int size
	) {
		UserDetailPageResponse response = userAdminFacade.getUsers(PageRequest.of(page, size));
		return ResponseEntity.ok(response);
	}
}

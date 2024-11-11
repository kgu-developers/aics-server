package kgu.developers.api.user.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import kgu.developers.api.user.application.UserService;
import kgu.developers.api.user.presentation.request.UserCreateRequest;
import kgu.developers.api.user.presentation.request.UserUpdateRequest;
import kgu.developers.api.user.presentation.response.UserDetailPageResponse;
import kgu.developers.api.user.presentation.response.UserDetailResponse;
import kgu.developers.api.user.presentation.response.UserPersistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "회원 관리 API")
public class UserController {
	private final UserService userService;

	@Operation(summary = "회원 가입 API", description = """
			- Description : 이 API는 유저를 생성하고 회원 가입 처리를 합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = UserPersistResponse.class)))
	@PostMapping("/signup")
	public ResponseEntity<UserPersistResponse> signup(
		@Valid @RequestBody UserCreateRequest request
	) {
		UserPersistResponse response = userService.createUser(request);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Operation(summary = "마이페이지", description = """
			- Description : 이 API는 회원의 정보를 출력합니다.
			- Assignee : 이신행
		""")
	@ApiResponse(
		responseCode = "200",
		description = "마이페이지 로드 완료",
		content = @Content(schema = @Schema(implementation = UserDetailResponse.class))
	)
	@GetMapping("/my")
	public ResponseEntity<UserDetailResponse> myPage() {
		UserDetailResponse response = userService.getUserDetail();
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "회원 정보 수정 API", description = """
			- Description : 이 API는 회원의 전화번호, 생년월일, 이메일 정보를 수정 합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(responseCode = "204", content = @Content(schema = @Schema(implementation = UserUpdateRequest.class)))
	@PatchMapping
	public ResponseEntity<Void> updateUser(
		@Valid @RequestBody UserUpdateRequest request
	) {
		userService.updateUser(request);
		return ResponseEntity.noContent().build();
	}

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
		UserDetailPageResponse response = userService.getUsers(PageRequest.of(page, size));
		return ResponseEntity.ok(response);
	}
}

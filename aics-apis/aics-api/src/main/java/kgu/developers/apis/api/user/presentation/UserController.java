package kgu.developers.apis.api.user.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import kgu.developers.apis.api.user.application.UserService;
import kgu.developers.apis.api.user.presentation.request.UserCreateRequest;
import kgu.developers.apis.api.user.presentation.request.UserUpdateRequest;
import kgu.developers.apis.api.user.presentation.response.UserDetailResponse;
import kgu.developers.apis.api.user.presentation.response.UserPersistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
	private final UserService userService;

	@Operation(summary = "유저 생성", description = "유저를 생성합니다.")
	@ApiResponse(
		responseCode = "201",
		description = "유저 생성 성공",
		content = @Content(schema = @Schema(implementation = UserPersistResponse.class))
	)
	@PostMapping("/signup")
	public ResponseEntity<UserPersistResponse> signup(
		@Valid @RequestBody UserCreateRequest request
	) {
		UserPersistResponse response = userService.createUser(request);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Operation(summary = "마이페이지", description = "유저의 마이페이지를 반환합니다.")
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

	@PatchMapping
	@ApiResponse(
		responseCode = "204",
		description = "유저 정보 수정 성공",
		content = @Content(schema = @Schema(implementation = UserUpdateRequest.class)))
	public ResponseEntity<Void> updateUser(
		@Valid @RequestBody UserUpdateRequest request
	) {
		userService.updateUser(request);
		return ResponseEntity.noContent().build();
	}
}

package kgu.developers.apis.api.user.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kgu.developers.apis.api.user.application.UserService;
import kgu.developers.apis.api.user.presentation.request.UserCreateRequest;
import kgu.developers.apis.api.user.presentation.request.UserUpdateRequest;
import kgu.developers.apis.api.user.presentation.response.UserPersistResponse;
import lombok.RequiredArgsConstructor;

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
}

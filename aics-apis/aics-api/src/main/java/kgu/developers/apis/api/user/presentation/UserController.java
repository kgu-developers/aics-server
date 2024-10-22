package kgu.developers.apis.api.user.presentation;

import static org.springframework.http.HttpStatus.CREATED;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import kgu.developers.apis.api.user.application.UserService;
import kgu.developers.apis.api.user.presentation.request.UserCreateRequest;
import kgu.developers.apis.api.user.presentation.request.UserUpdateRequest;
import kgu.developers.apis.api.user.presentation.response.UserPersistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PatchMapping()
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

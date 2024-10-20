package kgu.developers.apis.api.user.presentation;

import static org.springframework.http.HttpStatus.CREATED;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kgu.developers.apis.api.user.application.UserService;
import kgu.developers.apis.api.user.presentation.request.UserCreateRequest;
import kgu.developers.apis.api.user.presentation.response.UserPersistResponse;
import kgu.developers.core.common.exception.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
	@ApiResponses({
		@ApiResponse(
			responseCode = "201",
			description = "유저 생성 성공",
			content = @Content(schema = @Schema(implementation = UserPersistResponse.class))
		),
		@ApiResponse(
			responseCode = "409",
			description = "중복된 닉네임 입니다.",
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
		)
	})
	@PostMapping("/signup")
	public ResponseEntity<UserPersistResponse> signup(
		@Valid @RequestBody UserCreateRequest request
	) {
		UserPersistResponse response = userService.createUser(request);
		return ResponseEntity.status(CREATED).body(response);
	}
}

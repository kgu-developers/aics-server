package kgu.developers.apis.api.user.presentation;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import kgu.developers.apis.api.user.presentation.response.UserDetailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import kgu.developers.apis.api.user.application.UserService;
import kgu.developers.apis.api.user.presentation.request.UserCreateRequest;
import kgu.developers.apis.api.user.presentation.response.UserPersistResponse;
import lombok.RequiredArgsConstructor;

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
	@GetMapping
	public ResponseEntity<UserDetailResponse> myPage(){
		UserDetailResponse response = userService.getUserDetail();
		return ResponseEntity.status(OK).body(response);
	}
}

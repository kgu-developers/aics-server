package kgu.developers.api.user.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kgu.developers.api.user.presentation.request.UserCreateRequest;
import kgu.developers.api.user.presentation.request.UserPasswordUpdateRequest;
import kgu.developers.api.user.presentation.request.UserUpdateRequest;
import kgu.developers.api.user.presentation.response.UserPersistResponse;
import kgu.developers.domain.user.application.response.UserDetailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User", description = "회원 API")
public interface UserController {

	@Operation(summary = "회원 가입 API", description = """
			- Description : 이 API는 유저를 생성하고 회원 가입 처리를 합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(
		responseCode = "201",
		content = @Content(schema = @Schema(implementation = UserPersistResponse.class)))
	ResponseEntity<UserPersistResponse> signup(
		@Parameter(
			description = "회원 가입 request 객체 입니다.",
			required = true
		) @Valid @RequestBody UserCreateRequest request
	);

	@Operation(summary = "마이페이지 조회 API", description = """
			- Description : 이 API는 회원의 정보를 출력합니다.
			- Assignee : 이신행
		""")
	@ApiResponse(
		responseCode = "200",
		content = @Content(schema = @Schema(implementation = UserDetailResponse.class)))
	ResponseEntity<UserDetailResponse> myPage();

	@Operation(summary = "회원 정보 수정 API", description = """
			- Description : 이 API는 회원의 전화번호, 생년월일, 이메일 정보를 수정 합니다.
			- Assignee : 박민준
		""")
	@ApiResponse(
		responseCode = "204",
		content = @Content(schema = @Schema(implementation = UserUpdateRequest.class)))
	ResponseEntity<Void> updateUser(
		@Parameter(
			description = "회원 정보 수정 request 객체 입니다.",
			required = true
		) @Valid @RequestBody UserUpdateRequest request
	);

	@Operation(summary = "회원 비밀번호 수정 API", description = """
			- Description : 이 API는 회원의 비밀번호를 수정 합니다.
			- Assignee : 이신행
		""")
	@ApiResponse(responseCode = "204")
	ResponseEntity<Void> updatePassword(
		@Parameter(
			description = "회원 비밀번호 수정 request 객체 입니다.",
			required = true
		) @Valid @RequestBody UserPasswordUpdateRequest request
	);

	@Operation(summary = "회원 탈퇴 API", description = """
			- Description : 이 API는 회원을 탈퇴 시킵니다.
			- Assignee : 이신행
		""")
	@ApiResponse(responseCode = "204")
	public ResponseEntity<Void> delete();
}

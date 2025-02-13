package kgu.developers.api.user.presentation;

import jakarta.validation.Valid;
import kgu.developers.api.user.application.UserFacade;
import kgu.developers.api.user.presentation.request.UserCreateRequest;
import kgu.developers.api.user.presentation.request.UserPasswordUpdateRequest;
import kgu.developers.api.user.presentation.request.UserUpdateRequest;
import kgu.developers.api.user.presentation.response.UserPersistResponse;
import kgu.developers.domain.user.application.response.UserDetailResponse;
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
public class UserControllerImpl implements UserController {
	private final UserFacade userFacade;

	@Override
	@PostMapping("/signup")
	public ResponseEntity<UserPersistResponse> signup(
		@Valid @RequestBody UserCreateRequest request
	) {
		UserPersistResponse response = userFacade.createUser(request);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Override
	@GetMapping("/my")
	public ResponseEntity<UserDetailResponse> myPage() {
		UserDetailResponse response = userFacade.getUserDetail();
		return ResponseEntity.ok(response);
	}

	@Override
	@PatchMapping
	public ResponseEntity<Void> updateUser(
		@Valid @RequestBody UserUpdateRequest request
	) {
		userFacade.updateUser(request);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PatchMapping("/password")
	public ResponseEntity<Void> updatePassword(
		@Valid @RequestBody UserPasswordUpdateRequest request
	) {
		userFacade.updatePassword(request);
		return ResponseEntity.noContent().build();
	}
}

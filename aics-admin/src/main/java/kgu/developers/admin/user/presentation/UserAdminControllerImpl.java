package kgu.developers.admin.user.presentation;

import jakarta.validation.Valid;
import kgu.developers.admin.user.presentation.request.UserKickOutListRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import kgu.developers.admin.user.application.UserAdminFacade;
import kgu.developers.admin.user.presentation.response.UserDetailPageResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserAdminControllerImpl implements UserAdminController {
	private final UserAdminFacade userAdminFacade;

	@Override
	@GetMapping
	public ResponseEntity<UserDetailPageResponse> getUsersByName(
		@PositiveOrZero @RequestParam(defaultValue = "0") int page,
		@Positive @RequestParam(defaultValue = "10") int size,
		@RequestParam (required = false) String name
	) {
		UserDetailPageResponse response = userAdminFacade.getUsersByName(PageRequest.of(page, size), name);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping("/delete")
	public ResponseEntity<Void> kickOutUser(
		@Valid @RequestBody UserKickOutListRequest request
	) {
		userAdminFacade.kickOutUser(request);
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping("/cleanup-last-run")
	public ResponseEntity<String> getLastCleanupRunTime() {
		String response = userAdminFacade.getLastCleanupRunTime();
		return ResponseEntity.ok(response);
	}
}

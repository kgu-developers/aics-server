package kgu.developers.api.user.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.api.user.presentation.request.UserCreateRequest;
import kgu.developers.api.user.presentation.request.UserUpdateRequest;
import kgu.developers.api.user.presentation.response.UserPersistResponse;
import kgu.developers.domain.user.application.command.UserCommandService;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.application.response.UserDetailResponse;
import kgu.developers.domain.user.domain.User;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@Component
@Transactional
@RequiredArgsConstructor
public class UserFacade {
	private final UserQueryService userQueryService;
	private final UserCommandService userCommandService;

	public UserPersistResponse createUser(UserCreateRequest request) {
		String id = userCommandService.createUser(request.userId(), request.password(), request.name(), request.email(),
			request.phone(), request.major());
		return UserPersistResponse.of(id);
	}

	public void updateUser(UserUpdateRequest request) {
		User user = userQueryService.me();
		userCommandService.updateUserDetails(user, request.email(), request.phone());
	}

	public UserDetailResponse getUserDetail() {
		return userQueryService.getUserDetail();
	}
}

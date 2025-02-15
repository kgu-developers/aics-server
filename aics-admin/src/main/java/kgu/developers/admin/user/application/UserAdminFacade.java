package kgu.developers.admin.user.application;

import kgu.developers.admin.user.presentation.request.UserKickOutRequest;
import kgu.developers.domain.user.application.command.UserCommandService;
import kgu.developers.domain.user.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.admin.user.presentation.response.UserDetailPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserAdminFacade {
	private final UserCommandService userCommandService;
	private final UserQueryService userQueryService;

	public UserDetailPageResponse getUsers(Pageable pageable) {
		PaginatedListResponse response = userQueryService.getUsers(pageable);
		return UserDetailPageResponse.of(response.contents(), response.pageable());
	}

	@Transactional
	public void kickOutUser(UserKickOutRequest request) {
		User user = userQueryService.getUserById(request.userId());
		userCommandService.deleteUser(user);
		System.out.println(user.getId());
	}
}

package kgu.developers.admin.user.application;

import kgu.developers.admin.user.presentation.request.UserKickOutListRequest;
import kgu.developers.admin.user.presentation.response.UserDetailPageResponse;
import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.user.application.command.UserCommandService;
import kgu.developers.domain.user.application.command.UserSchedulingService;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserAdminFacade {
	private final UserCommandService userCommandService;
	private final UserQueryService userQueryService;
	private final UserSchedulingService userSchedulingService;

	public UserDetailPageResponse getUsersByName(Pageable pageable, String name) {
		PaginatedListResponse response = userQueryService.getUsersByName(pageable, name);
		return UserDetailPageResponse.of(response.contents(), response.pageable());
	}

	@Transactional
	public void kickOutUser(UserKickOutListRequest request) {
		List<User> users = userQueryService.getAllUsersByIds(request.userIds());
		users.forEach(User::validateDeletable);
		users.forEach(userCommandService::deleteUser);
	}

	public String getLastCleanupRunTime() {
		return userSchedulingService.getFormattedLastCleanupRunTime();
	}
}

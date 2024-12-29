package kgu.developers.admin.user.application;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.admin.user.presentation.response.UserDetailPageResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserAdminFacade {
	private final UserQueryService userQueryService;

	public UserDetailPageResponse getUsers(Pageable pageable) {
		PaginatedListResponse response = userQueryService.getUsers(pageable);
		return UserDetailPageResponse.of(response.contents(), response.pageable());
	}
}

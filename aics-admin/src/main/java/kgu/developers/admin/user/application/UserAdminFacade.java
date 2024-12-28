package kgu.developers.admin.user.application;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import kgu.developers.domain.user.application.UserQueryService;
import kgu.developers.domain.user.application.response.UserDetailPageResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserAdminFacade {
	private final UserQueryService userQueryService;

	public UserDetailPageResponse getUsers(Pageable pageable) {
		return userQueryService.getUsers(pageable);
	}
}

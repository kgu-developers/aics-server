package kgu.developers.domain.user.application;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.user.application.response.UserDetailPageResponse;
import kgu.developers.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {
	private final UserRepository userRepository;

	public UserDetailPageResponse getUsers(Pageable pageable) {
		PaginatedListResponse response = userRepository.findAllOrderByIdDesc(pageable);
		return UserDetailPageResponse.of(response.contents(), response.pageable());
	}
}

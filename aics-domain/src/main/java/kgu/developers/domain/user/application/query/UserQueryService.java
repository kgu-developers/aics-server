package kgu.developers.domain.user.application.query;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.user.application.response.UserDetailResponse;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.domain.UserRepository;
import kgu.developers.domain.user.exception.UserNotAuthenticatedException;
import kgu.developers.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {
	private final UserRepository userRepository;

	public PaginatedListResponse getUsers(Pageable pageable) {
		return userRepository.findAllOrderByIdDesc(pageable);
	}

	public User getUserById(String id) {
		return userRepository.findById(id)
			.orElseThrow(UserNotFoundException::new);
	}

	public UserDetailResponse getUserDetail() {
		User user = me();
		return UserDetailResponse.from(user);
	}

	public User me() {
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String userId = ((UserDetails)principal).getUsername();
			return getUserById(userId);
		} catch (Exception e) {
			throw new UserNotAuthenticatedException();
		}
	}
}

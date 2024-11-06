package kgu.developers.api.user.application;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.api.user.presentation.exception.UserIdDuplicateException;
import kgu.developers.api.user.presentation.exception.UserNotAuthenticatedException;
import kgu.developers.api.user.presentation.request.UserCreateRequest;
import kgu.developers.api.user.presentation.request.UserUpdateRequest;
import kgu.developers.api.user.presentation.response.UserDetailPageResponse;
import kgu.developers.api.user.presentation.response.UserDetailResponse;
import kgu.developers.api.user.presentation.response.UserPersistResponse;
import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.domain.UserRepository;
import kgu.developers.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserRepository userRepository;

	@Transactional
	public UserPersistResponse createUser(UserCreateRequest request) {
		validateDuplicateId(request.userId());

		User createUser = User.create(
			request.userId(),
			bCryptPasswordEncoder.encode(request.password()),
			request.name(),
			request.email(),
			request.phone(),
			request.major()
		);

		String id = userRepository.save(createUser).getId();
		return UserPersistResponse.of(id);
	}

	@Transactional
	public void updateUser(UserUpdateRequest request) {
		User updateUser = me();
		updateUser.updateEmail(request.email());
		updateUser.updatePhone(request.phone());
	}

	@Transactional(readOnly = true)
	public UserDetailPageResponse getUsers(Pageable pageable) {
		PaginatedListResponse response = userRepository.findAllOrderByIdDesc(pageable);
		return UserDetailPageResponse.of(response.contents(), response.pageable());
	}

	private void validateDuplicateId(String id) {
		if (userRepository.existsById(id)) {
			throw new UserIdDuplicateException();
		}
	}

	public User getUserById(String id) {
		return userRepository.findById(id)
			.orElseThrow(UserNotFoundException::new);
	}

	@Transactional(readOnly = true)
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

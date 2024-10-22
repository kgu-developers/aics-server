package kgu.developers.apis.api.user.application;

import jakarta.transaction.Transactional;
import kgu.developers.apis.api.major.application.MajorService;
import kgu.developers.apis.api.user.presentation.exception.UserEmailDuplicateException;
import kgu.developers.apis.api.user.presentation.exception.UserNotAuthenticatedException;
import kgu.developers.apis.api.user.presentation.exception.UserPersonalIdDuplicateException;
import kgu.developers.apis.api.user.presentation.exception.UserPhoneNumberDuplicateException;
import kgu.developers.apis.api.user.presentation.request.UserCreateRequest;
import kgu.developers.apis.api.user.presentation.request.UserUpdateRequest;
import kgu.developers.apis.api.user.presentation.response.UserDetailResponse;
import kgu.developers.apis.api.user.presentation.response.UserPersistResponse;
import kgu.developers.core.domain.major.domain.Major;
import kgu.developers.core.domain.user.domain.User;
import kgu.developers.core.domain.user.domain.UserRepository;
import kgu.developers.core.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserRepository userRepository;
	private final MajorService majorService;

	@Transactional
	public UserPersistResponse createUser(UserCreateRequest request) {
		validateDuplicatePersonalId(request.personalId());
		validateDuplicateEmail(request.email());
		validateDuplicatePhoneNumber(request.phoneNumber());

		Major major = majorService.getMajorByName(request.majorName());
		User createUser = User.create(
			request.personalId(),
			bCryptPasswordEncoder.encode(request.password()),
			request.name(),
			request.email(),
			request.phoneNumber(),
			request.birth(),
			request.gender(),
			request.grade(),
			major
		);

		Long id = userRepository.save(createUser).getId();
		return UserPersistResponse.of(id);
	}

	@Transactional
	public void updateUser(UserUpdateRequest request) {
		User updateUser = me();
		updateUser.updateEmail(request.email());
		updateUser.updatePhoneNumber(request.phoneNumber());
		updateUser.updateBirth(request.birth());
	}

	private void validateDuplicatePersonalId(String personalId) {
		if (userRepository.existsByPersonalId(personalId)) {
			throw new UserPersonalIdDuplicateException();
		}
	}

	public User getUserByPersonalId(String personalId) {
		return userRepository.findByPersonalId(personalId)
			.orElseThrow(UserNotFoundException::new);
	}

	private void validateDuplicateEmail(String email) {
		if (userRepository.existsByEmail(email)) {
			throw new UserEmailDuplicateException();
		}
	}

	private void validateDuplicatePhoneNumber(String phoneNumber) {
		if (userRepository.existsByPhoneNumber(phoneNumber)) {
			throw new UserPhoneNumberDuplicateException();
		}
	}

	@Transactional
	public UserDetailResponse getUserDetail() {
		User user = me();
		return UserDetailResponse.from(user);
	}

	public User me() {
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String personalId = ((UserDetails) principal).getUsername();
			return getUserByPersonalId(personalId);
		} catch (Exception e) {
			throw new UserNotAuthenticatedException();
		}
	}
}

package kgu.developers.apis.api.user.application;

import jakarta.transaction.Transactional;
import kgu.developers.apis.api.user.presentation.exception.UserEmailDuplicateException;
import kgu.developers.apis.api.user.presentation.exception.UserNotAuthenticatedException;
import kgu.developers.apis.api.user.presentation.exception.UserPersonalIdDuplicateException;
import kgu.developers.apis.api.user.presentation.exception.UserPhoneNumberDuplicateException;
import kgu.developers.apis.api.user.presentation.request.UserCreateRequest;
import kgu.developers.apis.api.user.presentation.request.UserUpdateRequest;
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
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	public UserPersistResponse createUser(UserCreateRequest request) {
		validateDuplicatePersonalId(request.personalId());
		/*
		validateDuplicateEmail(request.email());
		validateDuplicatePhoneNumber(request.phoneNumber());
		*/

		User createUser = User.create(
			request.personalId(),
			bCryptPasswordEncoder.encode(request.password()),
			request.name(),
			/*
			request.email(),
			request.phoneNumber(),
			*/
			request.birth(),
			request.gender(),
			request.grade(),
			//TODO: 메이저 이름으로 db에서 가져오는 메소드 구현 후 저장
			Major.create("컴퓨터공학부")
			//request.majorName()
		);

		Long id = userRepository.save(createUser).getId();
		return UserPersistResponse.of(id);
	}

	@Transactional
	public void updateUser(UserUpdateRequest request) {
		User updateUser = this.me();
		updateUser.updateEmail(request.email());
		updateUser.updatePhoneNumber(request.phoneNumber());
		updateUser.updateBirth(request.birth());
	}

	private void validateDuplicatePersonalId(String personalId) {
		if (userRepository.existsByPersonalId(personalId)) {
			throw new UserPersonalIdDuplicateException();
		}
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

	public User getUserByPersonalId(String personalId) {
		return userRepository.findByPersonalId(personalId)
			.orElseThrow(UserNotFoundException::new);
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

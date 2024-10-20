package kgu.developers.apis.api.user.application;

import jakarta.transaction.Transactional;
import kgu.developers.apis.api.user.presentation.exception.UserNotAuthenticatedException;
import kgu.developers.apis.api.user.presentation.exception.UserPersonalIdDuplicateException;
import kgu.developers.apis.api.user.presentation.request.UserCreateRequest;
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
		User createUser = User.create(
			request.personalId(),
			bCryptPasswordEncoder.encode(request.password()),
			request.name(),
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

	private void validateDuplicatePersonalId(String personalId) {
		if (userRepository.existsByPersonalId(personalId)) {
			throw new UserPersonalIdDuplicateException();
		}
	}

	public User getUserByPersonalId(String personalId) {
		return userRepository.findByPersonalId(personalId)
			.orElseThrow(UserNotFoundException::new);
	}

	public User me() {
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String personalId = ((UserDetails)principal).getUsername();
			return getUserByPersonalId(personalId);
		} catch (Exception e) {
			throw new UserNotAuthenticatedException();
		}
	}
}

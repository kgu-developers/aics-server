package kgu.developers.apis.api.user.application;

import kgu.developers.apis.api.user.presentation.exception.UserPersonalIdDuplicateException;
import kgu.developers.apis.api.user.presentation.request.UserCreateRequest;
import kgu.developers.apis.api.user.presentation.response.UserPersistResponse;
import kgu.developers.core.common.config.SecurityConfig;
import kgu.developers.core.domain.user.domain.User;
import kgu.developers.core.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Import(SecurityConfig.class)
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserPersistResponse createUser(UserCreateRequest request) {
		validateDuplicatePersonalId(request.personalId());
		User createUser = User.create(
			request.personalId(),
			bCryptPasswordEncoder.encode(request.password()),
			request.name(),
			request.birth(),
			request.gender(),
			request.grade()
		);

		Long id = userRepository.save(createUser).getId();
		return UserPersistResponse.of(id);
	}

	private void validateDuplicatePersonalId(String personalId) {
		if (userRepository.existsByPersonalId(personalId)) {
			throw new UserPersonalIdDuplicateException();
		}
	}
}

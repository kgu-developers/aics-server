package kgu.developers.domain.user.application.command;

import kgu.developers.domain.user.domain.Major;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.domain.UserRepository;
import kgu.developers.domain.user.exception.DuplicatePasswordException;
import kgu.developers.domain.user.exception.InvalidPasswordException;
import kgu.developers.domain.user.exception.UserIdDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandService {
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserRepository userRepository;

	public String createUser(String userId, String password, String name, String email, String phone, Major major) {
		validateDuplicateId(userId);
		User user = User.create(userId, encodePassword(password), name, email, phone, major);
		return userRepository.save(user).getId();
	}

	public void updateUserDetails(User user, String email, String phone) {
		user.updateEmail(email);
		user.updatePhone(phone);
	}

	private void validateDuplicateId(String id) {
		if (userRepository.existsById(id))
			throw new UserIdDuplicateException();
	}

	public void updatePassword(User user, String originalPassword, String newPassword) {
		try {
			user.isPasswordMatching(newPassword, bCryptPasswordEncoder);
			throw new DuplicatePasswordException();
		} catch (InvalidPasswordException ignore) {
		}

		System.out.println(user.getPassword());
		System.out.println(encodePassword(newPassword));
		user.isPasswordMatching(originalPassword, bCryptPasswordEncoder);
		user.updatePassword(encodePassword(newPassword));
	}

	private String encodePassword(String password) {
		return bCryptPasswordEncoder.encode(password);
	}
}

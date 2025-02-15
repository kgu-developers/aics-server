package kgu.developers.domain.user.application.command;

import kgu.developers.domain.user.domain.Major;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.domain.UserRepository;
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
		User user = User.create(userId, password, name, email, phone, major,  bCryptPasswordEncoder);
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
		user.isNewPasswordMatching(newPassword, bCryptPasswordEncoder);
		user.isPasswordMatching(originalPassword, bCryptPasswordEncoder);
		user.updatePassword(newPassword,  bCryptPasswordEncoder);
	}

	public void deleteUser(User user) {
		user.delete();
	}
}

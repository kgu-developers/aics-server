package post.application;

import kgu.developers.api.user.application.UserService;
import kgu.developers.domain.user.domain.Major;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.domain.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class FakeUserService extends UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public FakeUserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super(bCryptPasswordEncoder, userRepository);
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public User me() {
		return User.create(
			"202411345",
			bCryptPasswordEncoder.encode("password1234"),
			"홍길동",
			"test@kyonggi.ac.kr",
			"010-1234-5678",
			Major.CSE);
	}
}

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

	private static final String TEST_STUDENT_ID = "202411345";
	private static final String TEST_PASSWORD = "password1234";
	private static final String TEST_NAME = "홍길동";
	private static final String TEST_EMAIL = "test@kyonggi.ac.kr";
	private static final String TEST_PHONE = "010-1234-5678";

	@Override
	public User me() {
		return User.create(
			TEST_STUDENT_ID,
			bCryptPasswordEncoder.encode(TEST_PASSWORD),
			TEST_NAME,
			TEST_EMAIL,
			TEST_PHONE,
			Major.CSE
		);
	}
}

package user.application;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kgu.developers.api.user.application.UserService;
import kgu.developers.domain.user.domain.Major;
import kgu.developers.domain.user.domain.User;
import mock.FakeUserRepository;

public class UserServiceTest {
	private UserService userService;
	@BeforeEach
	void init() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		FakeUserRepository fakeUserRepository = new FakeUserRepository();

		this.userService = UserService.builder()
			.userRepository(fakeUserRepository)
			.bCryptPasswordEncoder(bCryptPasswordEncoder)
			.build();

		fakeUserRepository.save(User.builder()
			.id("202411345")
			.password(bCryptPasswordEncoder.encode("password1234"))
			.name("홍길동")
			.email("test@kyonggi.ac.kr")
			.phone("010-1234-5678")
			.major(Major.CSE)
			.build());

		fakeUserRepository.save(User.builder()
			.id("202411346")
			.password(bCryptPasswordEncoder.encode("password5678"))
			.name("신짱구")
			.email("shin@kyonggi.ac.kr")
			.phone("010-5678-1234")
			.major(Major.AIT)
			.build());
	}
}

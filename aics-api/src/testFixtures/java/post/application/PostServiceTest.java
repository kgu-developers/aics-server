package post.application;

import kgu.developers.api.post.application.PostService;
import kgu.developers.api.user.application.UserService;
import kgu.developers.domain.post.domain.Category;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.user.domain.Major;
import kgu.developers.domain.user.domain.User;
import mock.FakePostRepository;
import mock.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PostServiceTest {
	private PostService postService;

	@BeforeEach
	public void init() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		FakeUserRepository fakeUserRepository = new FakeUserRepository();
		FakePostRepository fakePostRepository = new FakePostRepository();

		UserService userService = UserService.builder()
			.userRepository(fakeUserRepository)
			.bCryptPasswordEncoder(bCryptPasswordEncoder)
			.build();

		this.postService = PostService.builder()
			.userService(userService)
			.postRepository(fakePostRepository)
			.build();

		User user1 = User.create(
			"202411345",
			bCryptPasswordEncoder.encode("password1234"),
			"홍길동",
			"test@kyonggi.ac.kr",
			"010-1234-5678",
			Major.CSE);

		User user2 = User.create(
			"202411346",
			bCryptPasswordEncoder.encode("password5678"),
			"신짱구",
			"shin@kyonggi.ac.kr",
			"010-5678-1234",
			Major.AIT);

		fakeUserRepository.save(user1);
		fakeUserRepository.save(user2);

		fakePostRepository.save(Post.create(
			"테스트용 제목1", "테스트용 내용1",
			Category.DEPT_INFO, user1
		));

		fakePostRepository.save(Post.create(
			"테스트용 제목2", "테스트용 내용2",
			Category.DEPT_INFO, user1
		));
	}
}

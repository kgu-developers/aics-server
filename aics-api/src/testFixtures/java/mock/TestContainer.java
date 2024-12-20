package mock;

import kgu.developers.domain.refreshtoken.domain.RefreshTokenRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kgu.developers.api.auth.application.AuthService;
import kgu.developers.api.auth.presentation.AuthController;
import kgu.developers.api.post.application.PostService;
import kgu.developers.api.user.application.UserService;
import kgu.developers.common.auth.jwt.JwtProperties;
import kgu.developers.common.auth.jwt.TokenProvider;
import kgu.developers.domain.post.domain.PostRepository;
import kgu.developers.domain.user.domain.UserRepository;

public class TestContainer {
	public final UserRepository userRepository;
	public final UserService userService;
	public final AuthService authService;
	public final AuthController authController;
	public final PostService postService;
	public final PostRepository postRepository;
	public final RefreshTokenRepository refreshTokenRepository;

	public TestContainer() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		this.refreshTokenRepository = new FakeRefreshTokenRepository();
		this.userRepository = new FakeUserRepository();
		this.userService = UserService.builder()
			.userRepository(this.userRepository)
			.bCryptPasswordEncoder(bCryptPasswordEncoder)
			.build();
		this.authService = AuthService.builder()
			.userService(this.userService)
			.passwordEncoder(bCryptPasswordEncoder)
			.tokenProvider(TokenProvider.builder()
				.jwtProperties(new JwtProperties("testIssuer", "testSecretKey"))
				.build()
			)
			.refreshTokenRepository(this.refreshTokenRepository)
			.build();
		this.authController = AuthController.builder()
			.authService(this.authService)
			.build();

		this.postRepository = new FakePostRepository();
		this.postService = new PostService(postRepository, userService);
	}
}
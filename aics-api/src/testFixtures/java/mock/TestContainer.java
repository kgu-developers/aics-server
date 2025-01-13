package mock;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kgu.developers.api.auth.application.AuthService;
import kgu.developers.common.auth.jwt.JwtProperties;
import kgu.developers.common.auth.jwt.TokenProvider;
import kgu.developers.domain.club.application.query.ClubQueryService;
import kgu.developers.domain.club.domain.ClubRepository;
import kgu.developers.domain.post.domain.PostRepository;
import kgu.developers.domain.refreshtoken.domain.RefreshTokenRepository;
import kgu.developers.domain.user.domain.UserRepository;

public class TestContainer {
	public final UserRepository userRepository;
	// public final UserFacade userFacade;
	public final AuthService authService;
	// public final AuthController authController;
	// public final PostFacade postFacade;
	public final PostRepository postRepository;
	public final RefreshTokenRepository refreshTokenRepository;

	public final ClubRepository clubRepository;
	public final ClubQueryService clubQueryService;

	public TestContainer() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		this.refreshTokenRepository = new FakeRefreshTokenRepository();
		this.userRepository = new FakeUserRepository();
		// this.userFacade = UserFacade.builder()
		// .userRepository(this.userRepository)
		// .bCryptPasswordEncoder(bCryptPasswordEncoder)
		// .build();
		this.authService = AuthService.builder()
			// .userFacade(this.userFacade)
			.passwordEncoder(bCryptPasswordEncoder)
			.tokenProvider(TokenProvider.builder()
				.jwtProperties(new JwtProperties("testIssuer", "testSecretKey"))
				.build()
			)
			.refreshTokenRepository(this.refreshTokenRepository)
			.build();
		// this.authController = AuthController.builder()
		// 	.authService(this.authService)
		// 	.build();

		this.postRepository = new FakePostRepository();
		// this.postFacade = new PostFacade(postRepository, userFacade);
		this.clubRepository = new FakeClubRepository();
		this.clubQueryService = new ClubQueryService(clubRepository);
	}
}
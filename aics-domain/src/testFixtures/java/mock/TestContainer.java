package mock;

import static kgu.developers.domain.user.domain.Major.CSE;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import kgu.developers.domain.club.application.query.ClubQueryService;
import kgu.developers.domain.club.domain.ClubRepository;
import kgu.developers.domain.post.application.command.PostCommandService;
import kgu.developers.domain.post.application.query.PostQueryService;
import kgu.developers.domain.post.domain.PostRepository;
import kgu.developers.domain.professor.application.command.ProfessorCommandService;
import kgu.developers.domain.professor.application.query.ProfessorQueryService;
import kgu.developers.domain.professor.domain.ProfessorRepository;
import kgu.developers.domain.refreshtoken.domain.RefreshTokenRepository;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.domain.UserRepository;

public class TestContainer {
	public final UserRepository userRepository;
	public final UserQueryService userQueryService;

	public final RefreshTokenRepository refreshTokenRepository;

	public final ClubRepository clubRepository;
	public final ClubQueryService clubQueryService;

	public final PostQueryService postQueryService;
	public final PostCommandService postCommandService;
	public final PostRepository postRepository;

	public final ProfessorRepository professorRepository;
	public final ProfessorQueryService professorQueryService;
	public final ProfessorCommandService professorCommandService;

	public TestContainer() {
		this.professorRepository = new FakeProfessorRepository();
		this.professorQueryService = new ProfessorQueryService(professorRepository);
		this.professorCommandService = new ProfessorCommandService(professorRepository);

		this.refreshTokenRepository = new FakeRefreshTokenRepository();

		this.userRepository = new FakeUserRepository();
		this.userQueryService = new UserQueryService(userRepository);
		userRepository.save(User.builder()
			.id("202411345")
			.password("password1234")
			.name("홍길동")
			.email("test@kyonggi.ac.kr")
			.phone("010-1234-5678")
			.major(CSE)
			.build());

		UserDetails user = userQueryService.getUserById("202411345");
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(
			new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities())
		);

		this.postRepository = new FakePostRepository();
		this.postQueryService = new PostQueryService(postRepository);
		this.postCommandService = new PostCommandService(userQueryService, postRepository);

		this.clubRepository = new FakeClubRepository();
		this.clubQueryService = new ClubQueryService(clubRepository);
	}
}
package mock;

import static kgu.developers.domain.user.domain.Major.CSE;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kgu.developers.domain.about.application.command.AboutCommandService;
import kgu.developers.domain.about.application.query.AboutQueryService;
import kgu.developers.domain.about.domain.AboutRepository;
import kgu.developers.domain.club.application.command.ClubCommandService;
import kgu.developers.domain.club.application.query.ClubQueryService;
import kgu.developers.domain.club.domain.ClubRepository;
import kgu.developers.domain.comment.application.command.CommentCommandService;
import kgu.developers.domain.comment.application.query.CommentQueryService;
import kgu.developers.domain.comment.domain.CommentRepository;
import kgu.developers.domain.file.application.command.FileCommandService;
import kgu.developers.domain.file.application.query.FileQueryService;
import kgu.developers.domain.file.domain.FileRepository;
import kgu.developers.domain.lab.application.command.LabCommandService;
import kgu.developers.domain.lab.application.query.LabQueryService;
import kgu.developers.domain.lab.domain.LabRepository;
import kgu.developers.domain.post.application.command.PostCommandService;
import kgu.developers.domain.post.application.query.PostQueryService;
import kgu.developers.domain.post.domain.PostRepository;
import kgu.developers.domain.professor.application.command.ProfessorCommandService;
import kgu.developers.domain.professor.application.query.ProfessorQueryService;
import kgu.developers.domain.professor.domain.ProfessorRepository;
import kgu.developers.domain.refreshtoken.domain.RefreshTokenRepository;
import kgu.developers.domain.user.application.command.UserCommandService;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.domain.UserRepository;
import mock.repository.FakeAboutRepository;
import mock.repository.FakeClubRepository;
import mock.repository.FakeCommentRepository;
import mock.repository.FakeFileRepository;
import mock.repository.FakeLabRepository;
import mock.repository.FakePostRepository;
import mock.repository.FakeProfessorRepository;
import mock.repository.FakeRefreshTokenRepository;
import mock.repository.FakeUserRepository;

public class TestContainer {
	public final AboutRepository aboutRepository;
	public final AboutCommandService aboutCommandService;
	public final AboutQueryService aboutQueryService;

	public final ClubRepository clubRepository;
	public final ClubQueryService clubQueryService;
	public final ClubCommandService clubCommandService;

	public final CommentRepository commentRepository;
	public final CommentQueryService commentQueryService;
	public final CommentCommandService commentCommandService;

	public final UserRepository userRepository;
	public final UserQueryService userQueryService;
	public final UserCommandService userCommandService;

	public final RefreshTokenRepository refreshTokenRepository;

	public final PostQueryService postQueryService;
	public final PostCommandService postCommandService;
	public final PostRepository postRepository;

	public final ProfessorRepository professorRepository;
	public final ProfessorQueryService professorQueryService;
	public final ProfessorCommandService professorCommandService;

	public final FileRepository fileRepository;
	public final FileQueryService fileQueryService;
	public final FileCommandService fileCommandService;

	public final LabRepository labRepository;
	public final LabQueryService labQueryService;
	public final LabCommandService labCommandService;

	public TestContainer() {
		this.aboutRepository = new FakeAboutRepository();
		this.aboutQueryService = new AboutQueryService(aboutRepository);
		this.aboutCommandService = new AboutCommandService(aboutRepository);

		this.professorRepository = new FakeProfessorRepository();
		this.professorQueryService = new ProfessorQueryService(professorRepository);
		this.professorCommandService = new ProfessorCommandService(professorRepository);

		this.refreshTokenRepository = new FakeRefreshTokenRepository();

		this.userRepository = new FakeUserRepository();
		this.userQueryService = new UserQueryService(userRepository);
		this.userCommandService = new UserCommandService(new BCryptPasswordEncoder(), userRepository);
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

		this.fileRepository = new FakeFileRepository();
		this.fileQueryService = new FileQueryService(fileRepository);
		this.fileCommandService = new FileCommandService(fileRepository);

		this.postRepository = new FakePostRepository();
		this.postQueryService = new PostQueryService(postRepository, fileRepository, userRepository);
		this.postCommandService = new PostCommandService(userQueryService, postRepository);

		this.commentRepository = new FakeCommentRepository();
		this.commentQueryService = new CommentQueryService(commentRepository);
		this.commentCommandService = new CommentCommandService(postQueryService, userQueryService, commentRepository);

		this.labRepository = new FakeLabRepository();
		this.labQueryService = new LabQueryService(labRepository);
		this.labCommandService = new LabCommandService(labRepository);

		this.clubRepository = new FakeClubRepository();
		this.clubQueryService = new ClubQueryService(clubRepository);
		this.clubCommandService = new ClubCommandService(clubRepository);


	}
}
package mock;

import static kgu.developers.domain.user.domain.Major.CSE;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

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
import kgu.developers.domain.file.application.query.FileQueryService;
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
import mock.repository.FakeLabRepository;
import mock.repository.FakePostRepository;
import mock.repository.FakeProfessorRepository;
import mock.repository.FakeRefreshTokenRepository;
import mock.repository.FakeUserRepository;

public class FakeTestContainer {
	private final Map<Class<?>, Supplier<?>> suppliers = new HashMap<>();
	private final Map<Class<?>, Object> instances = new HashMap<>();

	public FakeTestContainer() {
		// Register suppliers for lazy initialization
		suppliers.put(AboutRepository.class, FakeAboutRepository::new);
		suppliers.put(AboutQueryService.class, () -> new AboutQueryService(get(AboutRepository.class)));
		suppliers.put(AboutCommandService.class, () -> new AboutCommandService(get(AboutRepository.class)));

		suppliers.put(ClubRepository.class, FakeClubRepository::new);
		suppliers.put(ClubQueryService.class, () -> new ClubQueryService(get(ClubRepository.class)));
		suppliers.put(ClubCommandService.class, () -> new ClubCommandService(get(ClubRepository.class), get(
			FileQueryService.class)));

		suppliers.put(ProfessorRepository.class, FakeProfessorRepository::new);
		suppliers.put(ProfessorQueryService.class, () -> new ProfessorQueryService(get(ProfessorRepository.class)));
		suppliers.put(ProfessorCommandService.class, () -> new ProfessorCommandService(get(ProfessorRepository.class)));

		suppliers.put(RefreshTokenRepository.class, FakeRefreshTokenRepository::new);

		suppliers.put(UserRepository.class, FakeUserRepository::new);
		suppliers.put(UserQueryService.class, () -> new UserQueryService(get(UserRepository.class)));
		suppliers.put(UserCommandService.class,
			() -> new UserCommandService(new BCryptPasswordEncoder(), get(UserRepository.class)));

		suppliers.put(PostRepository.class, FakePostRepository::new);
		suppliers.put(PostQueryService.class, () -> new PostQueryService(get(PostRepository.class)));
		suppliers.put(PostCommandService.class,
			() -> new PostCommandService(get(UserQueryService.class), get(PostRepository.class), get(FileQueryService.class)));

		suppliers.put(CommentRepository.class, FakeCommentRepository::new);
		suppliers.put(CommentQueryService.class, () -> new CommentQueryService(get(CommentRepository.class)));
		suppliers.put(CommentCommandService.class,
			() -> new CommentCommandService(get(PostQueryService.class), get(UserQueryService.class),
				get(CommentRepository.class)));

		suppliers.put(LabRepository.class, FakeLabRepository::new);
		suppliers.put(LabQueryService.class, () -> new LabQueryService(get(LabRepository.class)));
		suppliers.put(LabCommandService.class, () -> new LabCommandService(get(FileQueryService.class), get(LabRepository.class)));

		// Preload specific instances if necessary
		UserRepository userRepository = get(UserRepository.class);
		userRepository.save(User.builder()
			.id("202411345")
			.password("password1234")
			.name("홍길동")
			.email("test@kyonggi.ac.kr")
			.phone("010-1234-5678")
			.major(CSE)
			.build());

		UserDetails user = get(UserQueryService.class).getUserById("202411345");
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(
			new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities())
		);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clazz) {
		if (!instances.containsKey(clazz)) {
			instances.put(clazz, suppliers.get(clazz).get());
		}
		return (T) instances.get(clazz);
	}
}

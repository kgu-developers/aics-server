package mock;

import kgu.developers.domain.club.application.query.ClubQueryService;
import kgu.developers.domain.club.domain.ClubRepository;
import kgu.developers.domain.post.application.command.PostCommandService;
import kgu.developers.domain.post.application.query.PostQueryService;
import kgu.developers.domain.post.domain.PostRepository;
import kgu.developers.domain.refreshtoken.domain.RefreshTokenRepository;
import kgu.developers.domain.user.application.query.UserQueryService;
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

	public TestContainer() {
		this.refreshTokenRepository = new FakeRefreshTokenRepository();

		this.userRepository = new FakeUserRepository();
		this.userQueryService = new UserQueryService(userRepository);

		this.postRepository = new FakePostRepository();
		this.postQueryService = new PostQueryService(postRepository);
		this.postCommandService = new PostCommandService(userQueryService, postRepository);

		this.clubRepository = new FakeClubRepository();
		this.clubQueryService = new ClubQueryService(clubRepository);
	}
}
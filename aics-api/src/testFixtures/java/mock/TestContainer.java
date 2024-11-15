package mock;

import kgu.developers.api.user.application.UserService;
import kgu.developers.domain.user.domain.UserRepository;
import lombok.Builder;

public class TestContainer {
	public final UserRepository userRepository;
	public final UserService userService;

	@Builder
	public TestContainer() {
		this.userRepository = new FakeUserRepository();
		this.userService = UserService.builder()
			.userRepository(this.userRepository)
			.build();
	}
}

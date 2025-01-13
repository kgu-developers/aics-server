package user.application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import kgu.developers.admin.user.application.UserAdminFacade;
import kgu.developers.admin.user.presentation.response.UserDetailPageResponse;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.application.response.UserDetailResponse;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.domain.UserRepository;
import mock.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static kgu.developers.domain.user.domain.Major.CSE;

public class UserAdminFacadeTest {
	private UserAdminFacade userAdminFacade;
	private UserRepository fakeUserRepository;

	@BeforeEach
	public void init() {
		this.fakeUserRepository = new FakeUserRepository();
		UserQueryService userQueryService = new UserQueryService(fakeUserRepository);
		this.userAdminFacade = new UserAdminFacade(userQueryService);

		fakeUserRepository.save(User.builder()
			.id("202411001")
			.password("password1234")
			.name("홍길동")
			.email("hong1@kyonggi.ac.kr")
			.phone("010-0000-0001")
			.major(CSE)
			.build());

		fakeUserRepository.save(User.builder()
			.id("202411002")
			.password("password1234")
			.name("홍길동")
			.email("hong2@kyonggi.ac.kr")
			.phone("010-0000-0002")
			.major(CSE)
			.build());

		fakeUserRepository.save(User.builder()
			.id("202411003")
			.password("password1234")
			.name("홍길동")
			.email("hong3@kyonggi.ac.kr")
			.phone("010-0000-0003")
			.major(CSE)
			.build());
	}

	@Test
	@DisplayName("getUsers는 Club을 생성할 수 있다.")
	void getUsers_Success() {
		// given
		Pageable pageable = PageRequest.of(0, 10);

		// when
		UserDetailPageResponse users = userAdminFacade.getUsers(pageable);

		// then
		List<UserDetailResponse> contents = users.contents();
		assertEquals(3, contents.size());

		UserDetailResponse hong3 = contents.get(0);
		UserDetailResponse hong2 = contents.get(1);
		UserDetailResponse hong1 = contents.get(2);

		assertEquals("202411001", hong1.id());
		assertEquals("202411002", hong2.id());
		assertEquals("202411003", hong3.id());
	}
}

package user.application;

import static kgu.developers.domain.user.domain.Major.CSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import kgu.developers.admin.user.application.UserAdminFacade;
import kgu.developers.admin.user.presentation.response.UserDetailPageResponse;
import kgu.developers.common.response.PageableResponse;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.application.response.UserDetailResponse;
import kgu.developers.domain.user.domain.User;
import mock.repository.FakeUserRepository;

public class UserAdminFacadeTest {
	private UserAdminFacade userAdminFacade;

	@BeforeEach
	public void init() {
		FakeUserRepository fakeUserRepository = new FakeUserRepository();
		this.userAdminFacade = new UserAdminFacade(
			new UserQueryService(fakeUserRepository)
		);

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
	@DisplayName("getUsers는 유저 목록을 페이징해서 조회한다")
	public void getUsers_Success() {
		// given
		Pageable pageable = PageRequest.of(0, 10);

		// when
		UserDetailPageResponse result = userAdminFacade.getUsersByName(pageable, null);

		// then
		List<UserDetailResponse> contents = result.contents();
		PageableResponse pageableResult = result.pageable();
		assertEquals(3, contents.size());

		UserDetailResponse hong3 = contents.get(0);
		UserDetailResponse hong2 = contents.get(1);
		UserDetailResponse hong1 = contents.get(2);

		assertEquals(10, pageableResult.size());
		assertEquals("202411001", hong1.id());
		assertEquals("202411002", hong2.id());
		assertEquals("202411003", hong3.id());
	}

	@Test
	@DisplayName("getUsers는 잘못된 페이지 요청시 빈 목록을 반환한다")
	public void getUsers_InvalidPage() {
		// given
		Pageable pageable = PageRequest.of(1, 10);

		// when
		UserDetailPageResponse users = userAdminFacade.getUsersByName(pageable, null);

		// then
		assertTrue(users.contents().isEmpty());
	}
}

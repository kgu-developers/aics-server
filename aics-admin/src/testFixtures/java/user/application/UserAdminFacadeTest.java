package user.application;

import static kgu.developers.common.domain.BaseRole.ADMIN;
import static kgu.developers.domain.user.domain.Major.CSE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import kgu.developers.admin.user.presentation.request.UserKickOutListRequest;
import kgu.developers.domain.user.application.command.UserCommandService;
import kgu.developers.domain.user.application.command.UserSchedulingService;
import kgu.developers.domain.user.exception.NotDeletableUserException;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserAdminFacadeTest {
	private UserAdminFacade userAdminFacade;
	private User user1;
	private User user2;

	@BeforeEach
	public void init() {
		FakeUserRepository fakeUserRepository = new FakeUserRepository();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.userAdminFacade = new UserAdminFacade(
			new UserCommandService(passwordEncoder, fakeUserRepository),
			new UserQueryService(fakeUserRepository),
			new UserSchedulingService(fakeUserRepository)
		);

		user1 = fakeUserRepository.save(User.builder()
			.id("202411001")
			.password("password1234")
			.name("홍길동")
			.email("hong1@kyonggi.ac.kr")
			.phone("010-0000-0001")
			.major(CSE)
			.build());

		user2 = fakeUserRepository.save(User.builder()
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
			.role(ADMIN)
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

	@Test
	@DisplayName("kickOutUser는 회원을 삭제한다")
	public void kickOutUser_Success() {
		// given
		UserKickOutListRequest request = UserKickOutListRequest.builder()
			.userIds(new ArrayList<>(Collections.singleton("202411001")))
			.build();

		// when
		userAdminFacade.kickOutUser(request);

		// then
		assertNotNull(user1.getDeletedAt());
	}

	@Test
	@DisplayName("kickOutUser는 여러 회원을 삭제한다")
	public void kickOutUserList_Success() {
		new ArrayList<>();
		// given
		UserKickOutListRequest request = UserKickOutListRequest.builder()
			.userIds(Arrays.asList("202411001", "202411002"))
			.build();

		// when
		userAdminFacade.kickOutUser(request);

		// then
		assertNotNull(user1.getDeletedAt());
		assertNotNull(user2.getDeletedAt());
	}

	@Test
	@DisplayName("kickOutUser는 관리자 삭제 요청의 경우 NotDeletableUserException을 발생시킨다")
	public void kickOutUser_ThrowsException() {
		// given
		UserKickOutListRequest request = UserKickOutListRequest.builder()
			.userIds(new ArrayList<>(Collections.singleton("202411003")))
			.build();

		// when
		// then
		assertThatThrownBy(() -> userAdminFacade.kickOutUser(request))
			.isInstanceOf(NotDeletableUserException.class);
	}

	@Test
	@DisplayName("getLastCleanupRunTime는 마지막 scheduling cleaning 시간을 조회한다")
	void getLastCleanupRunTime_Success() {
		// when
		String lastCleanupRunTime = userAdminFacade.getLastCleanupRunTime();

		// then
		assertEquals("아직 클린업 작업이 실행되지 않았습니다.", lastCleanupRunTime);
	}
}

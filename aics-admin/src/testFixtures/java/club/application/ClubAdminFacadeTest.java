package club.application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.admin.club.application.ClubAdminFacade;
import kgu.developers.admin.club.presentation.request.ClubCreateRequest;
import kgu.developers.admin.club.presentation.request.ClubUpdateRequest;
import kgu.developers.admin.club.presentation.response.ClubPersistResponse;
import kgu.developers.domain.club.application.command.ClubCommandService;
import kgu.developers.domain.club.application.query.ClubQueryService;
import kgu.developers.domain.club.domain.Club;
import kgu.developers.domain.club.exception.ClubNotFoundException;
import mock.repository.FakeClubRepository;

public class ClubAdminFacadeTest {
	private ClubAdminFacade clubAdminFacade;
	private FakeClubRepository fakeClubRepository;

	@BeforeEach
	public void init() {

		this.fakeClubRepository = new FakeClubRepository();
		this.clubAdminFacade = new ClubAdminFacade(
			new ClubCommandService(fakeClubRepository),
			new ClubQueryService(fakeClubRepository)
		);

		fakeClubRepository.save(
			Club.create(
				"C-Lab", "경기대학교 AI컴퓨터공학부 개발동아리입니다.", "https://www.clab.page", null
			)
		);
	}

	@Test
	@DisplayName("createClub은 Club을 생성한다")
	void createClub_Success() {
		// given
		ClubCreateRequest clubCreateRequest = new ClubCreateRequest(
			"newClub",
			"New Club Description",
			"https://www.new-club.page"
		);
		Long fileId = 1L;

		// when
		ClubPersistResponse result = clubAdminFacade.createClub(fileId, clubCreateRequest);
		List<Club> resultData = fakeClubRepository.findAll();

		// then
		assertEquals(2, resultData.size());
		assertEquals(2L, result.id());
	}

	@Test
	@DisplayName("updateClub은 Club을 수정한다")
	void updateClub_Success() {
		// given
		ClubUpdateRequest request = new ClubUpdateRequest(
			"newClub",
			"New Club Description",
			"https://www.new-club.page",
			1L
		);

		// when
		clubAdminFacade.updateClub(1L, request);
		Club saved = fakeClubRepository.findById(1L).get();

		// then
		assertEquals("newClub", saved.getName());
		assertEquals("New Club Description", saved.getDescription());
		assertEquals("https://www.new-club.page", saved.getSite());
	}

	@Test
	@DisplayName("updateClub은 존재하지 않은 id을 수정하면 ClubNotFoundException을 발생시킨다")
	void updateClub_throws_ClubNotFoundException() {
		// given
		ClubUpdateRequest request = new ClubUpdateRequest(
			"newClub",
			"New Club Description",
			"https://www.new-club.page",
			1L
		);

		// when
		// then
		assertThatThrownBy(() -> clubAdminFacade.updateClub(2L, request))
			.isInstanceOf(ClubNotFoundException.class);
	}

	@Test
	@DisplayName("deleteClub은 Club을 삭제한다")
	void deleteClub_Success() {
		// when
		clubAdminFacade.deleteClub(1L);

		// then
		List<Club> all = fakeClubRepository.findAll();
		assertEquals(0, all.size());
	}
}

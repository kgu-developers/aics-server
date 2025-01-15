package club.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.club.application.command.ClubCommandService;
import kgu.developers.domain.club.domain.Club;
import mock.repository.FakeClubRepository;

public class ClubCommandServiceTest {
	private ClubCommandService clubCommandService;

	@BeforeEach
	public void init() {
		clubCommandService = new ClubCommandService(
			new FakeClubRepository()
		);
	}

	@Test
	@DisplayName("createClub은 Club 객체를 생성한다.")
	public void createClub_Success() {
		//given
		String name = "Club c";
		String description = "c 동아리입니다.";
		String site = "http://club-c.kyonggi.ac.kr";

		//when
		Long result = clubCommandService.createClub(name, description, site);

		//then
		assertEquals(1L, result);
	}

	@Test
	@DisplayName("updateClub은 Club 객체를 수정한다.")
	public void updateClub_Success() {
		//when
		Club club = Club.create("a", "a 동아리", "http://club-a.kyonggi.ac.kr");

		String newName = "b";
		String newDescription = "b 동아리";
		String newSite = "http://club-b.kyonggi.ac.kr";

		//when
		clubCommandService.updateClub(club, newName, newDescription, newSite);

		//then
		assertEquals(newName, club.getName());
		assertEquals(newDescription, club.getDescription());
		assertEquals(newSite, club.getSite());
	}
}

package club.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.club.application.command.ClubCommandService;
import kgu.developers.domain.club.domain.Club;
import mock.FakeClubRepository;

public class ClubCommandServiceTest {
	private ClubCommandService clubCommandService;
	private FakeClubRepository fakeClubRepository;

	@BeforeEach
	public void init() {
		fakeClubRepository = new FakeClubRepository();
		clubCommandService = new ClubCommandService(fakeClubRepository);

		fakeClubRepository.save(
			Club.builder()
				.name("Club a")
				.description("a 동아리입니다.")
				.site("http://club-a.kyonggi.ac.kr")
				.build()
		);

		fakeClubRepository.save(
			Club.builder()
				.name("Club b")
				.description("b 동아리입니다.")
				.site("http://club-b.kyonggi.ac.kr")
				.build()
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
		Long id = clubCommandService.createClub(name, description, site);

		//then
		Club club = fakeClubRepository.findById(id).orElseThrow();
		assertEquals(id, 3L);
		assertEquals(name, club.getName());
		assertEquals(description, club.getDescription());
		assertEquals(site, club.getSite());
	}

	@Test
	@DisplayName("updateClub은 Club 객체를 수정한다.")
	public void updateClub_Success() {
		//when
		Club club = fakeClubRepository.findById(2L).orElseThrow();
		String newName = "b";
		String newDescription = "b 동아리";
		String newSite = "http://club-b.kyonggi.ac.kr";

		//when
		clubCommandService.updateClub(club, newName, newDescription, newSite);

		//then
		assertEquals(newName, club.getName());
		assertEquals(newDescription, club.getDescription());
	}
}

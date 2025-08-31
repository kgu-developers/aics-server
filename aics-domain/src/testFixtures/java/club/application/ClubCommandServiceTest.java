package club.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.club.application.command.ClubCommandService;
import kgu.developers.domain.club.domain.Club;
import kgu.developers.domain.file.application.query.FileQueryService;
import mock.repository.FakeClubRepository;
import mock.repository.FakeFileRepository;

public class ClubCommandServiceTest {
	private ClubCommandService clubCommandService;
	private FakeClubRepository fakeClubRepository;

	@BeforeEach
	public void init() {
		FakeFileRepository fakeFileRepository = new FakeFileRepository();
		FileQueryService fileQueryService = new FileQueryService(fakeFileRepository);
		fakeClubRepository = new FakeClubRepository();
		clubCommandService = new ClubCommandService(fakeClubRepository, fileQueryService);

		fakeClubRepository.save(
			Club.create("club",
				"description",
				"http://club.kyonggi.ac.kr",
				null
			)
		);
	}

	@Test
	@DisplayName("createClub은 Club 객체를 생성한다.")
	public void createClub_Success() {
		// given
		String name = "Club a";
		String description = "a 동아리입니다.";
		String site = "http://club-a.kyonggi.ac.kr";

		// when
		Long result = clubCommandService.createClub(name, description, site, null);

		// then
		assertEquals(2L, result);
	}

	@Test
	@DisplayName("updateClub은 Club 객체를 수정한다.")
	public void updateClub_Success() {
		// given
		Club club = Club.create("a", "a 동아리", "http://club-a.kyonggi.ac.kr", null);

		String newName = "b";
		String newDescription = "b 동아리";
		String newSite = "http://club-b.kyonggi.ac.kr";

		// when
		clubCommandService.updateClub(club, newName, newDescription, newSite, null);

		// then
		assertEquals(newName, club.getName());
		assertEquals(newDescription, club.getDescription());
		assertEquals(newSite, club.getSite());
		assertNull(club.getFileId());
	}

	@Test
	@DisplayName("deleteClubById는 Club 객체를 삭제한다")
	public void deleteClubById_Success() {
		// given
		Long id = 1L;

		// when
		clubCommandService.deleteClubById(id);

		// then
		Club result = fakeClubRepository.findById(id).orElse(null);
		assertNull(result);
	}
}

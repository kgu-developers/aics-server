package club.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import kgu.developers.domain.file.application.query.FileQueryService;
import mock.repository.FakeFileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.api.club.application.ClubFacade;
import kgu.developers.api.club.presentation.response.ClubListResponse;
import kgu.developers.domain.club.application.query.ClubQueryService;
import kgu.developers.domain.club.domain.Club;
import mock.repository.FakeClubRepository;

public class ClubFacadeTest {
	private ClubFacade clubFacade;

	@BeforeEach
	public void init() {
		FakeClubRepository fakeClubRepository = new FakeClubRepository();
		FakeFileRepository fakeFileRepository = new FakeFileRepository();

		clubFacade = new ClubFacade(
			new ClubQueryService(fakeClubRepository),new FileQueryService(fakeFileRepository)
		);

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
	@DisplayName("getClubs는 동아리 리스트를 반환한다")
	public void getClubs_Success() {
		// when
		ClubListResponse result = clubFacade.getClubs();

		// then
		assertEquals(2, result.contents().size());
		assertEquals("Club a", result.contents().get(0).name());
		assertEquals("Club b", result.contents().get(1).name());
	}
}

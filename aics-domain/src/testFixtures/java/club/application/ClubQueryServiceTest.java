package club.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.club.application.query.ClubQueryService;
import kgu.developers.domain.club.domain.Club;
import mock.TestContainer;

public class ClubQueryServiceTest {
	private ClubQueryService clubQueryService;

	@BeforeEach
	public void init() {
		TestContainer testContainer = new TestContainer();
		clubQueryService = testContainer.clubQueryService;

		testContainer.clubRepository.save(
			Club.builder()
				.name("Club a")
				.description("a 동아리입니다.")
				.site("http://club-a.kyonggi.ac.kr")
				.build()
		);

		testContainer.clubRepository.save(
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
		List<Club> result = clubQueryService.getClubs();

		// then
		assertEquals(2, result.size());
		assertEquals("Club a", result.get(0).getName());
		assertEquals("Club b", result.get(1).getName());
	}

	@Test
	@DisplayName("getById는 해당 id의 동아리를 반환한다")
	public void getById_Success() {
		// when
		Club result = clubQueryService.getById(1L);

		// then
		assertEquals("Club a", result.getName());
		assertEquals("a 동아리입니다.", result.getDescription());
		assertEquals("http://club-a.kyonggi.ac.kr", result.getSite());

	}
}

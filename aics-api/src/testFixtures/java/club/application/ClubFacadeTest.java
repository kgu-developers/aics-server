package club.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.api.club.application.ClubFacade;
import kgu.developers.api.club.presentation.response.ClubListResponse;
import kgu.developers.domain.club.domain.Club;
import mock.TestContainer;

public class ClubFacadeTest {
	private ClubFacade clubFacade;

	@BeforeEach
	public void init() {
		TestContainer testContainer = new TestContainer();

		this.clubFacade = ClubFacade.builder()
			.clubQueryService(testContainer.clubQueryService)
			.build();

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
	@DisplayName("getSortedProfessorList는 정렬된 교수 리스트를 반환한다")
	public void getSortedProfessorList_Success() {
		// when
		ClubListResponse result = clubFacade.getClubs();

		// then
		assertEquals(2, result.contents().size());
		assertEquals("Club a", result.contents().get(0).name());
		assertEquals("Club a", result.contents().get(0).name());
	}
}

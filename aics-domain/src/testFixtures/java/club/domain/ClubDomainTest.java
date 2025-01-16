package club.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.club.domain.Club;

public class ClubDomainTest {
	private Club club;
	private static final String DEFAULT_NAME = "Club A";
	private static final String DEFAULT_DESCRIPTION = "a 동아리입니다.";
	private static final String DEFAULT_SITE = "http://club-a.kyonggi.ac.kr";

	@BeforeEach
	void setUp() {
		club = Club.create(DEFAULT_NAME, DEFAULT_DESCRIPTION, DEFAULT_SITE);
	}

	@Test
	@DisplayName("Club 객체를 생성할 수 있다")
	public void createClub_Success() {

		// when
		Club newClub = Club.create(DEFAULT_NAME, DEFAULT_DESCRIPTION, DEFAULT_SITE);

		// then
		assertNotNull(newClub);
		assertEquals(DEFAULT_NAME, newClub.getName());
		assertEquals(DEFAULT_DESCRIPTION, newClub.getDescription());
		assertEquals(DEFAULT_SITE, newClub.getSite());
	}

	@Test
	@DisplayName("Club 동아리 이름을 수정할 수 있다")
	public void updateName_Success() {
		// given
		String newName = "Updated Club A";

		// when
		club.updateName(newName);

		// then
		assertEquals(newName, club.getName());
	}

	@Test
	@DisplayName("Club 설명을 수정할 수 있다")
	public void updateDescription_Success() {
		String newDescription = "club a 동아리입니다.";

		// when
		club.updateDescription(newDescription);

		// then
		assertEquals(newDescription, club.getDescription());
	}

	@Test
	@DisplayName("Club 사이트를 수정할 수 있다")
	public void updateSite_Success() {
		// given
		String newSite = "http://Club-a.kyonggi.ac.kr";

		// when
		club.updateSite(newSite);

		// then
		assertEquals(newSite, club.getSite());
	}
}

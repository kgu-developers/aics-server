package club.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.club.domain.Club;

public class ClubDomainTest {
	@Test
	@DisplayName("Club 객체를 생성할 수 있다")
	public void createClub_Success() {
		//given
		String name = "Club A";
		String description = "a 동아리입니다.";
		String site = "http://club-a.kyonggi.ac.kr";

		//when
		Club club = Club.create(name, description, site);

		//then
		assertNotNull(club);
		assertEquals(name, club.getName());
		assertEquals(description, club.getDescription());
		assertEquals(site, club.getSite());
	}

	@Test
	@DisplayName("Club 동아리 이름을 수정할 수 있다")
	public void updateName_Success() {
		//given
		String name = "Club A";
		String description = "a 동아리입니다.";
		String site = "http://club-a.kyonggi.ac.kr";
		Club club = Club.create(name, description, site);

		String newName = "Updated Club A";

		//when
		club.updateName(newName);

		//then
		assertEquals(newName, club.getName());
	}

	@Test
	@DisplayName("Club 설명을 수정할 수 있다")
	public void updateDescription_Success() {
		//given
		String name = "Club A";
		String description = "a 동아리입니다.";
		String site = "http://club-a.kyonggi.ac.kr";
		Club club = Club.create(name, description, site);

		String newDescription = "club a 동아리입니다.";

		//when
		club.updateDescription(newDescription);

		//then
		assertEquals(newDescription, club.getDescription());
	}

	@Test
	@DisplayName("Club 사이트를 수정할 수 있다")
	public void updateSite_Success() {
		//given
		String name = "Club A";
		String description = "a 동아리입니다.";
		String site = "http://club-a.kyonggi.ac.kr";
		Club club = Club.create(name, description, site);

		String newSite = "http://Club-a.kyonggi.ac.kr";

		//when
		club.updateSite(newSite);

		//then
		assertEquals(newSite, club.getSite());
	}
}

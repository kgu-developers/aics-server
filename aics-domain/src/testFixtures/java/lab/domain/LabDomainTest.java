package lab.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import kgu.developers.domain.lab.domain.Lab;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LabDomainTest {
	private Lab lab;
	private final String NAME = "Lab A";
	private final String LOC = "8500";
	private final String SITE = "http://lab1.kyonggi.ac.kr";


	@BeforeEach
	void setUp() {
		lab = Lab.create(NAME, LOC, SITE);
	}

	@Test
	@DisplayName("LAB 객체를 생성할 수 있다")
	public void createLab_Success() {
		//given
		setUp();

		//when
		//then
		assertNotNull(lab);
		assertEquals(NAME, lab.getName());
		assertEquals(LOC, lab.getLoc());
		assertEquals(SITE, lab.getSite());
	}


	@Test
	@DisplayName("LAB 얀구실명 수정할 수 있다")
	public void updateName_Success() {
		//given
		setUp();
		String newName = "Updated Lab A";

		//when
		lab.updateName(newName);

		//then
		assertEquals(newName, lab.getName());
	}

	@Test
	@DisplayName("LAB 위치를 수정할 수 있다")
	public void updateLoc_Success() {
		//given
		setUp();
		String newLoc = "8601";

		//when
		lab.updateLoc(newLoc);

		//then
		assertEquals(newLoc, lab.getLoc());
	}

	@Test
	@DisplayName("LAB 사이트를 수정할 수 있다")
	public void updateSite_Success() {
		//given
		setUp();
		String newSite = "http://new.kyonggi.ac.kr";

		//when
		lab.updateSite(newSite);

		//then
		assertEquals(newSite, lab.getSite());
	}

}

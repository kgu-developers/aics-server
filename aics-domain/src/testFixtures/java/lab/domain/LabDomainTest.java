package lab.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.lab.domain.Lab;

public class LabDomainTest {
	private Lab lab;
	private static final String NAME = "Lab A";
	private static final String LOC = "8500";
	private static final String SITE = "http://lab1.kyonggi.ac.kr";
	private static final String ADVISOR = "박교수";

	@BeforeEach
	public void init() {
		lab = Lab.create(NAME, LOC, SITE, ADVISOR);
	}

	@Test
	@DisplayName("LAB 객체를 생성할 수 있다")
	public void createLab_Success() {
		// when
		// then
		assertNotNull(lab);
		assertEquals(NAME, lab.getName());
		assertEquals(LOC, lab.getLoc());
		assertEquals(SITE, lab.getSite());
		assertEquals(ADVISOR, lab.getAdvisor());

	}

	@Test
	@DisplayName("LAB 얀구실명 수정할 수 있다")
	public void updateName_Success() {
		// given
		String newName = "Updated Lab A";

		// when
		lab.updateName(newName);

		// then
		assertEquals(newName, lab.getName());
	}

	@Test
	@DisplayName("LAB 위치를 수정할 수 있다")
	public void updateLoc_Success() {
		// given
		String newLoc = "8601";

		// when
		lab.updateLoc(newLoc);

		// then
		assertEquals(newLoc, lab.getLoc());
	}

	@Test
	@DisplayName("LAB 사이트를 수정할 수 있다")
	public void updateSite_Success() {
		// given
		String newSite = "http://new.kyonggi.ac.kr";

		// when
		lab.updateSite(newSite);

		// then
		assertEquals(newSite, lab.getSite());
	}

	@Test
	@DisplayName("LAB 담당교수를 수정할 수 있다")
	public void updateAdvisor_Success() {
		// given
		String newAdvisor = "이교수";

		// when
		lab.updateAdvisor(newAdvisor);

		// then
		assertEquals(newAdvisor, lab.getAdvisor());
	}
}

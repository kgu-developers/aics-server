package lab.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import kgu.developers.domain.lab.domain.Lab;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LabDomainTest {
	@Test
	@DisplayName("LAB 객체를 생성할 수 있다")
	public void createLab_Success() {
		//given
		String name = "Lab A";
		String loc = "8500";
		String site = "http://lab1.kyonggi.ac.kr";

		//when
		Lab lab = Lab.create(name, loc, site);

		//then
		assertNotNull(lab);
		assertEquals(name, lab.getName());
		assertEquals(loc, lab.getLoc());
		assertEquals(site, lab.getSite());
	}


	@Test
	@DisplayName("LAB 얀구실명 수정할 수 있다")
	public void updateName_Success() {
		//given
		String name = "Lab A";
		String loc = "8500";
		String site = "http://lab1.kyonggi.ac.kr";
		Lab lab = Lab.create(name, loc, site);

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
		String name = "Lab A";
		String loc = "8500";
		String site = "http://lab1.kyonggi.ac.kr";
		Lab lab = Lab.create(name, loc, site);

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
		String name = "Lab A";
		String loc = "8500";
		String site = "http://lab1.kyonggi.ac.kr";
		Lab lab = Lab.create(name, loc, site);

		String newSite = "http://new.kyonggi.ac.kr";

		//when
		lab.updateSite(newSite);

		//then
		assertEquals(newSite, lab.getSite());
	}

}

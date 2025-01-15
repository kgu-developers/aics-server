package lab.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.lab.application.command.LabCommandService;
import kgu.developers.domain.lab.domain.Lab;
import mock.FakeLabRepository;

public class LabCommandServiceTest {
	private LabCommandService labCommandService;

	@BeforeEach
	public void init() {
		FakeLabRepository fakeLabRepository = new FakeLabRepository();
		labCommandService = new LabCommandService(fakeLabRepository);
	}

	@Test
	@DisplayName("createLab은 Lab을 생성할 수 있다")
	public void createLab_Success() {
		// given
		String name = "Lab B";
		String loc = "8501";
		String site = "http://labB.kyonggi.ac.kr";
		String advisor = "박교수";

		// when
		Long result = labCommandService.createLab(name, loc, site, advisor);

		// then
		assertEquals(1L, result);
	}

	@Test
	@DisplayName("updateLab은 Lab을 수정할 수 있다")
	public void updateLab_Success() {
		// given
		Lab lab = Lab.builder().build();
		String newName = "Lab AA";
		String newLoc = "제2공학관 200";
		String newSite = "https://labAA.kyonggi.ac.kr";
		String newAdvisor = "professor park";

		// when
		labCommandService.updateLab(lab, newName, newLoc, newSite, newAdvisor);

		// then
		assertEquals(newName, lab.getName());
		assertEquals(newLoc, lab.getLoc());
		assertEquals(newSite, lab.getSite());
		assertEquals(newAdvisor, lab.getAdvisor());
	}

	@Test
	@DisplayName("deleteLab은 Lab을 삭제할 수 있다")
	public void deleteLab_Success() {
		// given
		String name = "Lab A";
		String loc = "8501";
		String site = "http://labA.kyonggi.ac.kr";
		String advisor = "박교수";

		Long labId = labCommandService.createLab(name, loc, site, advisor);

		// when
		labCommandService.deleteLabById(labId);

		// then TODO: 추후 구현
	}
}

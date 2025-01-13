package lab.application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.lab.application.command.LabCommandService;
import kgu.developers.domain.lab.domain.Lab;
import mock.FakeLabRepository;

public class LabCommandServiceTest {
	private LabCommandService labCommandService;
	private FakeLabRepository fakeLabRepository;

	@BeforeEach
	public void init() {
		fakeLabRepository = new FakeLabRepository();
		this.labCommandService = new LabCommandService(fakeLabRepository);

		fakeLabRepository.save(Lab.builder()
			.name("Lab A")
			.loc("8500")
			.site("http://labA.kyonggi.ac.kr")
			.advisor("박민준")
			.build()
		);
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
		Long labId = labCommandService.createLab(name, loc, site, advisor);

		// then
		Lab lab = fakeLabRepository.findById(labId).orElse(null);
		assertNotNull(lab);
		assertEquals(2, lab.getId());
		assertEquals("Lab B", lab.getName());
		assertEquals("8501", lab.getLoc());
		assertEquals("http://labB.kyonggi.ac.kr", lab.getSite());
		assertEquals("박교수", lab.getAdvisor());
	}

	@Test
	@DisplayName("updateLab은 Lab을 수정할 수 있다")
	public void updateLab_Success() {
		// given
		Lab lab = fakeLabRepository.findById(1L).orElse(null);
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
		Long id = 1L;

		// when
		labCommandService.deleteLabById(id);

		// then
		Optional<Lab> deletedLab = fakeLabRepository.findById(id);
		assertThat(deletedLab).isEmpty();
	}
}

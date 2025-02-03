package lab.application;

import kgu.developers.domain.file.application.query.FileQueryService;
import kgu.developers.domain.file.domain.FileEntity;
import kgu.developers.domain.lab.application.command.LabCommandService;
import kgu.developers.domain.lab.domain.Lab;
import mock.repository.FakeFileRepository;
import mock.repository.FakeLabRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LabCommandServiceTest {
	private LabCommandService labCommandService;
	private FakeLabRepository fakeLabRepository;

	private static final Long TARGET_LAB_ID = 2L;
	private static final Long TEST_FILE_ID = 1L;

	@BeforeEach
	public void init() {
		initializeLabCommandService();
	}

	private void initializeLabCommandService() {
		FakeFileRepository fakeFileRepository = new FakeFileRepository();
		fakeLabRepository = new FakeLabRepository();
		labCommandService = new LabCommandService(new FileQueryService(fakeFileRepository), fakeLabRepository);
		fakeFileRepository.save(FileEntity.builder().id(1L).build());
		fakeLabRepository.save(saveTestLab());
	}

	private static Lab saveTestLab() {
		return Lab.create("인공지능 연구실", "8502, 8503", "http://ailab.kyonggi.ac.kr", "김인철",
			FileEntity.builder().id(TEST_FILE_ID).build());
	}

	@Test
	@DisplayName("createLab은 Lab을 생성할 수 있다")
	public void createLab_Success() {
		// given
		String name = "인공지능 연구실";
		String loc = "8502, 8503";
		String site = "http://ailab.kyonggi.ac.kr";
		String advisor = "김인철";

		// when
		Long createdLabId = labCommandService.createLab(TEST_FILE_ID, name, loc, site, advisor);

		// then
		assertEquals(TARGET_LAB_ID, createdLabId);
	}

	@Test
	@DisplayName("updateLab은 Lab을 수정할 수 있다")
	public void updateLab_Success() {
		// given
		Lab lab = saveTestLab();
		String targetName = "알고리즘 연구실";
		String targetLoc = "8504";
		String targetSite = "http://algeo.kyonggi.ac.kr/";
		String targetAdvisor = "배상원";

		// when
		labCommandService.updateLab(lab, targetName, targetLoc, targetSite, targetAdvisor);

		// then
		assertEquals(targetName, lab.getName());
		assertEquals(targetLoc, lab.getLoc());
		assertEquals(targetSite, lab.getSite());
		assertEquals(targetAdvisor, lab.getAdvisor());
	}

	@Test
	@DisplayName("deleteLab은 Lab을 삭제할 수 있다")
	public void deleteLab_Success() {
		// given
		String name = "인공지능 연구실";
		String loc = "8502, 8503";
		String site = "http://ailab.kyonggi.ac.kr";
		String advisor = "김인철";

		Long labIdToDelete = labCommandService.createLab(TEST_FILE_ID, name, loc, site, advisor);

		// when
		labCommandService.deleteLabById(labIdToDelete);

		// then
		assertEquals(Optional.empty(), fakeLabRepository.findById(labIdToDelete));
	}
}

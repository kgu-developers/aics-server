package lab.application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.admin.lab.application.LabAdminFacade;
import kgu.developers.admin.lab.presentation.request.LabRequest;
import kgu.developers.admin.lab.presentation.response.LabPersistResponse;
import kgu.developers.domain.lab.application.command.LabCommandService;
import kgu.developers.domain.lab.application.query.LabQueryService;
import kgu.developers.domain.lab.domain.Lab;
import mock.repository.FakeLabRepository;

public class LabAdminFacadeTest {
	private LabAdminFacade labAdminFacade;
	private FakeLabRepository fakeLabRepository;

	@BeforeEach
	public void init() {
		fakeLabRepository = new FakeLabRepository();
		labAdminFacade = new LabAdminFacade(
			new LabCommandService(fakeLabRepository),
			new LabQueryService(fakeLabRepository)
		);

		fakeLabRepository.save(Lab.builder()
			.name("Lab A")
			.loc("8500")
			.site("http://labA.kyonggi.ac.kr")
			.advisor("박민준")
			.build()
		);
	}

	@Test
	@DisplayName("createLab은 Lab을 생성한다")
	public void createLab_Success() {
		// given
		LabRequest request = LabRequest.builder()
			.name("Lab B")
			.loc("8501")
			.site("http://labB.kyonggi.ac.kr")
			.advisor("박교수")
			.build();

		// when
		LabPersistResponse response = labAdminFacade.createLab(request);

		// then
		Lab lab = fakeLabRepository.findById(response.id()).orElse(null);
		assertNotNull(lab);
		assertEquals(2, lab.getId());
		assertEquals(request.name(), lab.getName());
		assertEquals(request.loc(), lab.getLoc());
		assertEquals(request.site(), lab.getSite());
		assertEquals(request.advisor(), lab.getAdvisor());
	}

	@Test
	@DisplayName("updateLab은 Lab을 수정한다")
	public void updateLab_Success() {
		// given
		Long labId = 1L;
		LabRequest request = LabRequest.builder()
			.name("Lab B")
			.loc("8501")
			.site("http://labB.kyonggi.ac.kr")
			.advisor("박교수")
			.build();

		// when
		labAdminFacade.updateLab(labId, request);

		// then
		Lab lab = fakeLabRepository.findById(labId).orElse(null);
		assertNotNull(lab);
		assertEquals(1L, lab.getId());
		assertEquals(request.name(), lab.getName());
		assertEquals(request.loc(), lab.getLoc());
		assertEquals(request.site(), lab.getSite());
		assertEquals(request.advisor(), lab.getAdvisor());
	}

	@Test
	@DisplayName("deleteLab은 Lab을 삭제한다")
	public void deleteLab_Success() {
		// given
		Long labId = 1L;

		// when
		labAdminFacade.deleteLab(labId);

		// then
		Optional<Lab> deletedLab = fakeLabRepository.findById(labId);
		assertThat(deletedLab).isEmpty();
	}
}

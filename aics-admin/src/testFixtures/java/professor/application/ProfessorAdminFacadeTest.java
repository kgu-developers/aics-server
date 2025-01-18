package professor.application;

import static kgu.developers.domain.professor.domain.Role.ASSISTANT;
import static kgu.developers.domain.professor.domain.Role.PROFESSOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.admin.professor.application.ProfessorAdminFacade;
import kgu.developers.admin.professor.presentation.request.ProfessorRequest;
import kgu.developers.admin.professor.presentation.response.ProfessorPersistResponse;
import kgu.developers.domain.professor.application.command.ProfessorCommandService;
import kgu.developers.domain.professor.application.query.ProfessorQueryService;
import kgu.developers.domain.professor.domain.Professor;
import mock.repository.FakeProfessorRepository;

public class ProfessorAdminFacadeTest {
	private ProfessorAdminFacade professorAdminFacade;
	private FakeProfessorRepository fakeProfessorRepository;

	@BeforeEach
	public void init() {
		fakeProfessorRepository = new FakeProfessorRepository();

		professorAdminFacade = new ProfessorAdminFacade(
			new ProfessorCommandService(fakeProfessorRepository),
			new ProfessorQueryService(fakeProfessorRepository)
		);

		fakeProfessorRepository.save(Professor.builder()
			.email("alswns11346@kyonggi.ac.kr")
			.name("박민준")
			.role(ASSISTANT)
			.contact("010-1234-5678")
			.img("img1")
			.officeLoc("office1")
			.build());
	}

	@Test
	@DisplayName("createProfessor는 교수를 생성한다")
	public void createProfessor_Success() {
		// given
		ProfessorRequest request = ProfessorRequest.builder()
			.name("권기현")
			.role(PROFESSOR)
			.email("kkh1111@kgu.ac.kr")
			.contact("010-1234-5678")
			.img("kkhImg")
			.officeLoc("kkhOffice")
			.build();

		// when
		ProfessorPersistResponse result = professorAdminFacade.createProfessor(request);

		// then
		assertEquals(2, result.id());
	}

	@Test
	@DisplayName("updateProfessor는 교수를 수정한다")
	public void updateProfessor_Success() {
		// given
		Long professorId = 1L;
		ProfessorRequest request = ProfessorRequest.builder()
			.name("권기현")
			.role(PROFESSOR)
			.email("kkh1111@kgu.ac.kr")
			.contact("010-1234-5678")
			.img("kkhImg")
			.officeLoc("kkhOffice")
			.build();

		// when
		professorAdminFacade.updateProfessor(professorId, request);

		// then
		Professor professor = fakeProfessorRepository.findById(professorId).orElse(null);
		assertEquals(1, professor.getId());
		assertEquals(request.name(), professor.getName());
		assertEquals(request.email(), professor.getEmail());
		assertEquals(request.contact(), professor.getContact());
		assertEquals(request.role(), professor.getRole());
		assertEquals(request.img(), professor.getImg());
		assertEquals(request.officeLoc(), professor.getOfficeLoc());
	}

	@Test
	@DisplayName("deleteProfessor는 교수를 삭제한다")
	public void deleteProfessor_Success() {
		// given
		Long professorId = 1L;

		// when
		professorAdminFacade.deleteProfessor(professorId);

		// then
		Professor professor = fakeProfessorRepository.findById(professorId).orElse(null);
		assertNotNull(professor.getDeletedAt());
	}
}

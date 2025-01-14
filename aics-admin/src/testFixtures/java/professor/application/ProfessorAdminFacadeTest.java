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
import kgu.developers.domain.professor.domain.Professor;
import mock.TestContainer;

public class ProfessorAdminFacadeTest {
	private ProfessorAdminFacade professorAdminFacade;
	private TestContainer testContainer;

	@BeforeEach
	public void init() {
		testContainer = new TestContainer();

		professorAdminFacade = new ProfessorAdminFacade(
			testContainer.professorCommandService,
			testContainer.professorQueryService
		);

		testContainer.professorRepository.save(Professor.builder()
			.email("alswns11346@kyonggi.ac.kr")
			.name("박민준")
			.role(ASSISTANT)
			.contact("010-1234-5678")
			.img("img1")
			.officeLoc("office1")
			.build());
	}

	@Test
	@DisplayName("createProfessor는 교수를 생성할 수 있다")
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
		ProfessorPersistResponse response = professorAdminFacade.createProfessor(request);

		// then
		Professor professor = testContainer.professorRepository.findById(response.id()).orElse(null);
		assertEquals(2, professor.getId());
		assertEquals(request.name(), professor.getName());
		assertEquals(request.email(), professor.getEmail());
		assertEquals(request.contact(), professor.getContact());
		assertEquals(request.role(), professor.getRole());
		assertEquals(request.img(), professor.getImg());
		assertEquals(request.officeLoc(), professor.getOfficeLoc());
	}

	@Test
	@DisplayName("updateProfessor는 교수를 수정할 수 있다")
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
		Professor professor = testContainer.professorRepository.findById(professorId).orElse(null);
		assertEquals(1, professor.getId());
		assertEquals(request.name(), professor.getName());
		assertEquals(request.email(), professor.getEmail());
		assertEquals(request.contact(), professor.getContact());
		assertEquals(request.role(), professor.getRole());
		assertEquals(request.img(), professor.getImg());
		assertEquals(request.officeLoc(), professor.getOfficeLoc());
	}

	@Test
	@DisplayName("deleteProfessor는 교수를 삭제할 수 있다")
	public void deleteProfessor_Success() {
		// given
		Long professorId = 1L;

		// when
		professorAdminFacade.deleteProfessor(professorId);

		// then
		Professor professor = testContainer.professorRepository.findById(professorId).orElse(null);
		assertNotNull(professor.getDeletedAt());
	}
}

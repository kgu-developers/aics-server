package professor.application;

import static kgu.developers.domain.professor.domain.Role.ASSISTANT;
import static kgu.developers.domain.professor.domain.Role.PROFESSOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.professor.application.command.ProfessorCommandService;
import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.ProfessorRepository;
import kgu.developers.domain.professor.domain.Role;
import mock.TestContainer;

public class ProfessorCommandServiceTest {
	private ProfessorCommandService professorCommandService;
	private ProfessorRepository professorRepository;

	@BeforeEach
	public void init() {
		TestContainer testContainer = new TestContainer();
		professorRepository = testContainer.professorRepository;
		professorCommandService = testContainer.professorCommandService;

		professorRepository.save(Professor.builder()
			.email("alswns11346@kyonggi.ac.kr")
			.name("박민준")
			.role(ASSISTANT)
			.contact("010-1234-5678")
			.img("img1")
			.officeLoc("office1")
			.build());

		professorRepository.save(Professor.builder()
			.email("kkh@kyonggi.ac.kr")
			.name("권기현")
			.role(PROFESSOR)
			.contact("010-1234-5678")
			.img("img3")
			.officeLoc("office3")
			.build());
	}

	@Test
	@DisplayName("createProfessor는 교수를 생성할 수 있다")
	public void createProfessor_Success() {
		// given
		String name = "권기현";
		Role role = PROFESSOR;
		String email = "kkh1111@kgu.ac.kr";
		String contact = "010-1234-5678";
		String img = "kkhImg";
		String officeLoc = "kkhOffice";

		// when
		Long response = professorCommandService.createProfessor(name, role, contact, email, img, officeLoc);

		// then
		Professor professor = professorRepository.findById(response).orElse(null);
		assertEquals(3, response);
		assertEquals(name, professor.getName());
		assertEquals(email, professor.getEmail());
		assertEquals(contact, professor.getContact());
		assertEquals(role, professor.getRole());
		assertEquals(img, professor.getImg());
		assertEquals(officeLoc, professor.getOfficeLoc());
	}

	@Test
	@DisplayName("updateProfessor는 교수 정보를 수정할 수 있다")
	public void updateProfessor_Success() {
		// given
		Long professorId = 1L;
		String name = "붹뭰줸";
		Role role = PROFESSOR;
		String email = "alswns11346@kgu.ac.kr";
		String contact = "010-1234-5678";
		String img = "updateImage";
		String officeLoc = "updateOffice";

		// when
		Professor professor = professorRepository.findById(professorId).orElse(null);
		professorCommandService.updateProfessor(professor, name, role, contact, email, img, officeLoc);

		// then
		assertEquals(1L, professor.getId());
		assertEquals(name, professor.getName());
		assertEquals(email, professor.getEmail());
		assertEquals(contact, professor.getContact());
		assertEquals(role, professor.getRole());
		assertEquals(img, professor.getImg());
		assertEquals(officeLoc, professor.getOfficeLoc());
	}

	@Test
	@DisplayName("deleteProfessor는 교수를 삭제할 수 있다")
	public void deleteProfessor_Success() {
		// given
		Long professorId = 1L;

		// when
		Professor professor = professorRepository.findById(professorId).orElse(null);
		professorCommandService.deleteProfessor(professor);

		// then
		assertNotEquals(professor.getDeletedAt(), null);
	}
}

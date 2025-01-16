package professor.application;

import static kgu.developers.domain.professor.domain.Role.PROFESSOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.professor.application.command.ProfessorCommandService;
import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.Role;
import mock.repository.FakeProfessorRepository;

public class ProfessorCommandServiceTest {
	private ProfessorCommandService professorCommandService;

	@BeforeEach
	public void init() {
		professorCommandService = new ProfessorCommandService(
			new FakeProfessorRepository()
		);
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
		Long result = professorCommandService.createProfessor(name, role, contact, email, img, officeLoc);

		// then
		assertEquals(1L, result);
	}

	@Test
	@DisplayName("updateProfessor는 교수 정보를 수정할 수 있다")
	public void updateProfessor_Success() {
		// given
		Professor professor = Professor.builder().build();
		String name = "붹뭰줸";
		Role role = PROFESSOR;
		String email = "alswns11346@kgu.ac.kr";
		String contact = "010-1234-5678";
		String img = "updateImage";
		String officeLoc = "updateOffice";

		// when
		professorCommandService.updateProfessor(professor, name, role, contact, email, img, officeLoc);

		// then
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
		Professor professor = Professor.builder().build();
		assertNull(professor.getDeletedAt(), "삭제 전에는 deletedAt이 null이어야 합니다");
		// when
		professorCommandService.deleteProfessor(professor);

		// then
		assertNotNull(professor.getDeletedAt());
	}
}

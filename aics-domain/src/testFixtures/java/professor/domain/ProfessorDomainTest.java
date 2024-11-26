package professor.domain;

import static kgu.developers.domain.professor.domain.Role.PROFESSOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.Role;

public class ProfessorDomainTest {

	@Test
	@DisplayName("PROFESSOR 객체를 생성할 수 있다")
	public void createProfessor_Success() {
		// given
		String name = "박민준";
		Role role = PROFESSOR;
		String contact = "010-1234-5678";
		String email = "alswns11346@kgu.ac.kr";

		// when
		Professor professor = Professor.create(name, role, contact, email);

		// then
		assertNotNull(professor);
		assertEquals(name, professor.getName());
		assertEquals(role, professor.getRole());
		assertEquals(contact, professor.getContact());
		assertEquals(email, professor.getEmail());
	}
}

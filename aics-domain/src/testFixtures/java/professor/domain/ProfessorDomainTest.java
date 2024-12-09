package professor.domain;

import static kgu.developers.domain.professor.domain.Role.ASSISTANT;
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

	@Test
	@DisplayName("PROFESSOR 객체의 이름을 수정할 수 있다")
	public void updateName_Success() {
		// given
		String name = "박민준";
		Role role = PROFESSOR;
		String contact = "010-1234-5678";
		String email = "alswns11346@kgu.ac.kr";
		Professor professor = Professor.create(name, role, contact, email);

		// when
		String newName = "이신행";
		professor.updateName(newName);

		// then
		assertEquals(newName, professor.getName());
	}

	@Test
	@DisplayName("PROFESSOR 객체의 역할을 수정할 수 있다")
	public void updateRole_Success() {
		// given
		String name = "박민준";
		Role role = PROFESSOR;
		String contact = "010-1234-5678";
		String email = "alswns11346@kgu.ac.kr";
		Professor professor = Professor.create(name, role, contact, email);

		// when
		Role newRole = ASSISTANT;
		professor.updateRole(newRole);

		// then
		assertEquals(newRole, professor.getRole());
	}

	@Test
	@DisplayName("PROFESSOR 객체의 연락처를 수정할 수 있다")
	public void updateContact_Success() {
		// given
		String name = "박민준";
		Role role = PROFESSOR;
		String contact = "010-1234-5678";
		String email = "alswns11346@kgu.ac.kr";
		Professor professor = Professor.create(name, role, contact, email);

		// when
		String newContact = "010-1234-8765";
		professor.updateContact(newContact);

		// then
		assertEquals(newContact, professor.getContact());
	}

	@Test
	@DisplayName("PROFESSOR 객체의 메일을 수정할 수 있다")
	public void updateEmail_Success() {
		// given
		String name = "박민준";
		Role role = PROFESSOR;
		String contact = "010-1234-5678";
		String email = "alswns11346@kgu.ac.kr";
		Professor professor = Professor.create(name, role, contact, email);

		// when
		String newEmail = "new-email@kyonggi.ac.kr";
		professor.updateEmail(newEmail);

		// then
		assertEquals(newEmail, professor.getEmail());
	}
}

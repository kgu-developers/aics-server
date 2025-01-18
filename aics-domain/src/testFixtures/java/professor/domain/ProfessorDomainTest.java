package professor.domain;

import static kgu.developers.domain.professor.domain.Role.ASSISTANT;
import static kgu.developers.domain.professor.domain.Role.PROFESSOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.Role;

public class ProfessorDomainTest {
	private Professor professor;
	private static final String NAME = "박민준";
	private static final Role ROLE = PROFESSOR;
	private static final String CONTACT = "010-1234-5678";
	private static final String EMAIL = "alswns11346@kgu.ac.kr";
	private static final String IMAGE = "https://image.com/professor/profile/image";
	private static final String OFFICE_LOC = "8000호";

	@BeforeEach
	public void init() {
		professor = Professor.create(NAME, ROLE, CONTACT, EMAIL, IMAGE, OFFICE_LOC);
	}

	@Test
	@DisplayName("PROFESSOR 객체를 생성할 수 있다")
	public void createProfessor_Success() {

		// when
		// then
		assertNotNull(professor);
		assertEquals(NAME, professor.getName());
		assertEquals(ROLE, professor.getRole());
		assertEquals(CONTACT, professor.getContact());
		assertEquals(EMAIL, professor.getEmail());
		assertEquals(IMAGE, professor.getImg());
		assertEquals(OFFICE_LOC, professor.getOfficeLoc());
	}

	@Test
	@DisplayName("PROFESSOR 객체의 이름을 수정할 수 있다")
	public void updateName_Success() {

		// when
		String newName = "이신행";
		professor.updateName(newName);

		// then
		assertEquals(newName, professor.getName());
	}

	@Test
	@DisplayName("PROFESSOR 객체의 역할을 수정할 수 있다")
	public void updateRole_Success() {

		// when
		Role newRole = ASSISTANT;
		professor.updateRole(newRole);

		// then
		assertEquals(newRole, professor.getRole());
	}

	@Test
	@DisplayName("PROFESSOR 객체의 연락처를 수정할 수 있다")
	public void updateContact_Success() {

		// when
		String newContact = "010-1234-8765";
		professor.updateContact(newContact);

		// then
		assertEquals(newContact, professor.getContact());
	}

	@Test
	@DisplayName("PROFESSOR 객체의 메일을 수정할 수 있다")
	public void updateEmail_Success() {

		// when
		String newEmail = "new-email@kyonggi.ac.kr";
		professor.updateEmail(newEmail);

		// then
		assertEquals(newEmail, professor.getEmail());
	}
}

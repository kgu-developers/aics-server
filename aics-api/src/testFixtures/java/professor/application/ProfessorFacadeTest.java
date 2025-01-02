package professor.application;

import static kgu.developers.domain.professor.domain.Role.ASSISTANT;
import static kgu.developers.domain.professor.domain.Role.PROFESSOR;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.api.professor.application.ProfessorFacade;
import kgu.developers.domain.professor.exception.ProfessorNotFoundException;
import kgu.developers.api.professor.presentation.request.ProfessorRequest;
import kgu.developers.api.professor.presentation.response.ProfessorPersistResponse;
import kgu.developers.domain.professor.domain.Professor;
import mock.FakeProfessorRepository;
/*
public class ProfessorFacadeTest {
	private ProfessorFacade professorFacade;

	@BeforeEach
	public void init() {
		FakeProfessorRepository fakeProfessorRepository = new FakeProfessorRepository();

		this.professorFacade = ProfessorFacade.builder()
			.professorRepository(fakeProfessorRepository)
			.build();

		fakeProfessorRepository.save(Professor.builder()
			.email("alswns11346@kyonggi.ac.kr")
			.name("박민준")
			.role(ASSISTANT)
			.contact("010-1234-5678")
			.build());

		fakeProfessorRepository.save(Professor.builder()
			.email("alswns11346@kgu.ac.kr")
			.name("박민준")
			.role(PROFESSOR)
			.contact("010-1234-5678")
			.build());

		fakeProfessorRepository.save(Professor.builder()
			.email("kkh@kyonggi.ac.kr")
			.name("권기현")
			.role(PROFESSOR)
			.contact("010-1234-5678")
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
			.build();

		// when
		ProfessorPersistResponse response = professorFacade.createProfessor(request);
		Professor result = Professor.create(request.name(), request.role(), request.contact(), request.email());

		// then
		assertEquals(4, response.id());
		assertEquals("권기현", result.getName());
		assertEquals("kkh1111@kgu.ac.kr", result.getEmail());
		assertEquals("010-1234-5678", result.getContact());
		assertEquals(PROFESSOR, result.getRole());
	}

	@Test
	@DisplayName("updateProfessor는 교수 정보를 수정할 수 있다")
	public void updateProfessor_Success() {
		// given
		Long professorId = 2L;
		ProfessorRequest request = new ProfessorRequest("박민준", PROFESSOR, "010-9999-8888",
			"alswnszzang1@kyonggi.ac.kr");

		// when
		professorFacade.updateProfessor(professorId, request);
		Professor response = professorFacade.getProfessorById(professorId);

		// then
		assertEquals("박민준", response.getName());
		assertEquals(PROFESSOR, response.getRole());
		assertEquals("010-9999-8888", response.getContact());
		assertEquals("alswnszzang1@kyonggi.ac.kr", response.getEmail());
	}

	@Test
	@DisplayName("getProfessor는 존재하지 않는 교수를 찾아올 경우 ProfessorNotFoundException을 발생시킨다.")
	public void getProfessor_NotFound_ThrowsException() {
		// given
		Long professorId = 4L;

		// when
		// then
		assertThatThrownBy(() -> {
			professorFacade.getProfessorById(professorId);
		}).isInstanceOf(ProfessorNotFoundException.class);
	}

	@Test
	@DisplayName("deleteProfessor는 교수를 삭제할 수 있다")
	public void deleteProfessor_Success() {
		// given
		Long professorId = 1L;

		// when
		professorFacade.deleteProfessor(professorId);

		// then
		assertThatThrownBy(() -> {
			professorFacade.getProfessorById(professorId);
		}).isInstanceOf(ProfessorNotFoundException.class);
	}

	@Test
	@DisplayName("getSortedProfessorList는 정렬된 교수 리스트를 반환한다")
	public void getSortedProfessorList_Success() {
		// when
		List<Professor> result = professorFacade.getSortedProfessorList();

		// then
		assertEquals(3, result.size());
		assertEquals("권기현", result.get(0).getName());
		assertEquals(PROFESSOR, result.get(0).getRole());
		assertEquals("박민준", result.get(1).getName());
		assertEquals(PROFESSOR, result.get(1).getRole());
		assertEquals("박민준", result.get(2).getName());
		assertEquals(ASSISTANT, result.get(2).getRole());
	}
}
*/

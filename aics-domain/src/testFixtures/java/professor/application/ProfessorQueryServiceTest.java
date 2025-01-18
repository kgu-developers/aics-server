package professor.application;

import static kgu.developers.domain.professor.domain.Role.ASSISTANT;
import static kgu.developers.domain.professor.domain.Role.PROFESSOR;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.professor.application.query.ProfessorQueryService;
import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.exception.ProfessorNotFoundException;
import mock.repository.FakeProfessorRepository;

public class ProfessorQueryServiceTest {
	private ProfessorQueryService professorQueryService;

	@BeforeEach
	public void init() {
		FakeProfessorRepository fakeProfessorRepository = new FakeProfessorRepository();
		professorQueryService = new ProfessorQueryService(fakeProfessorRepository);

		fakeProfessorRepository.save(Professor.builder()
			.email("alswns11346@kyonggi.ac.kr")
			.name("박민준")
			.role(ASSISTANT)
			.contact("010-1234-5678")
			.img("img1")
			.officeLoc("office1")
			.build());

		fakeProfessorRepository.save(Professor.builder()
			.email("alswns11346@kgu.ac.kr")
			.name("박민준")
			.role(PROFESSOR)
			.contact("010-1234-5644")
			.img("img2")
			.officeLoc("office2")
			.build());

		fakeProfessorRepository.save(Professor.builder()
			.email("kkh@kyonggi.ac.kr")
			.name("권기현")
			.role(PROFESSOR)
			.contact("010-1234-5678")
			.img("img3")
			.officeLoc("office3")
			.build());
	}

	@Test
	@DisplayName("getProfessorById는 해당 교수를 조회할 수 있다")
	public void getProfessorById_Success() {
		// given
		Long professorId = 1L;

		// when
		Professor result = professorQueryService.getProfessorById(professorId);

		// then
		assertNotNull(result);
		assertEquals(professorId, result.getId());
		assertEquals("alswns11346@kyonggi.ac.kr", result.getEmail());
		assertEquals("박민준", result.getName());
		assertEquals(ASSISTANT, result.getRole());
		assertEquals("010-1234-5678", result.getContact());
		assertEquals("img1", result.getImg());
		assertEquals("office1", result.getOfficeLoc());
	}

	@Test
	@DisplayName("getProfessorById는 존재하지 않는 교수를 찾아올 경우 ProfessorNotFoundException을 발생시킨다.")
	public void getProfessorById_NotFound_ThrowsException() {
		// given
		Long professorId = 4L;

		// when
		// then
		assertThatThrownBy(() -> professorQueryService.getProfessorById(professorId))
			.isInstanceOf(ProfessorNotFoundException.class);
	}

	@Test
	@DisplayName("getSortedProfessorList는 정렬된 교수 리스트를 반환한다")
	public void getSortedProfessorList_Success() {
		// when
		List<Professor> result = professorQueryService.getSortedProfessorList();

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

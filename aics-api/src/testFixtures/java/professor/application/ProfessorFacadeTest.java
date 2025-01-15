package professor.application;

import static kgu.developers.domain.professor.domain.Role.ASSISTANT;
import static kgu.developers.domain.professor.domain.Role.PROFESSOR;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.api.professor.application.ProfessorFacade;
import kgu.developers.api.professor.presentation.response.ProfessorListResponse;
import kgu.developers.domain.professor.application.query.ProfessorQueryService;
import kgu.developers.domain.professor.application.response.ProfessorResponse;
import kgu.developers.domain.professor.domain.Professor;
import mock.repository.FakeProfessorRepository;

public class ProfessorFacadeTest {
	private ProfessorFacade professorFacade;

	@BeforeEach
	public void init() {
		FakeProfessorRepository fakeProfessorRepository = new FakeProfessorRepository();
		professorFacade = new ProfessorFacade(
			new ProfessorQueryService(fakeProfessorRepository)
		);

		fakeProfessorRepository.save(
			Professor.create(
				"권기현",
				PROFESSOR,
				"031-249-9666",
				"khkwon@kyonggi.ac.kr",
				"http://cs.kyonggi.ac.kr:8080/img/professor/20180209095754-%EA%B6%8C%EA%B8%B0%ED%98%84.jpeg",
				"8209호"
			)
		);

		fakeProfessorRepository.save(
			Professor.create(
				"임현기",
				ASSISTANT,
				"031-249-1318",
				"hlim20@kyonggi.ac.kr",
				"http://cs.kyonggi.ac.kr:8080/img/professor/20210428143902-%EC%9C%A4%EC%9B%90%ED%98%84.jpg",
				"8215호"
			)
		);
	}

	@Test
	@DisplayName("getSortedProfessorList 메서드는 정렬된 교수 리스트를 반환한다")
	public void getSortedProfessorList_Success() {
		// when
		ProfessorListResponse response = professorFacade.getSortedProfessorList();

		// then
		List<ProfessorResponse> professors = response.contents();
		assertEquals(2, professors.size());

		ProfessorResponse professor1 = professors.get(0);
		assertEquals("권기현", professor1.name());
		assertEquals(PROFESSOR.getDescription(), professor1.type());
		assertEquals("031-249-9666", professor1.contact());
		assertEquals("http://cs.kyonggi.ac.kr:8080/img/professor/20180209095754-%EA%B6%8C%EA%B8%B0%ED%98%84.jpeg",
			professor1.img());
		assertEquals("8209호", professor1.officeLoc());

		ProfessorResponse professor2 = professors.get(1);
		assertEquals("임현기", professor2.name());
		assertEquals(ASSISTANT.getDescription(), professor2.type());
		assertEquals("031-249-1318", professor2.contact());
		assertEquals("http://cs.kyonggi.ac.kr:8080/img/professor/20210428143902-%EC%9C%A4%EC%9B%90%ED%98%84.jpg",
			professor2.img());
		assertEquals("8215호", professor2.officeLoc());
	}
}

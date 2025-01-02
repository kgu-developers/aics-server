package kgu.developers.api.professor.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.api.professor.presentation.response.ProfessorListResponse;
import kgu.developers.domain.professor.application.query.ProfessorQueryService;
import kgu.developers.domain.professor.domain.Professor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfessorFacade {
	private final ProfessorQueryService professorQueryService;

	public ProfessorListResponse getSortedProfessorList() {
		List<Professor> list = professorQueryService.getSortedProfessorList();
		return ProfessorListResponse.from(list);
	}
}

package kgu.developers.api.professor.application;

import jakarta.validation.constraints.Positive;
import kgu.developers.api.professor.presentation.request.ProfessorRequest;
import kgu.developers.api.professor.presentation.response.ProfessorResponse;
import kgu.developers.api.professor.presentation.response.ProfessorPersistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

	// TODO
	public ProfessorPersistResponse createProfessor(ProfessorRequest request) {
		return null;
	}

	public void updateProfessor(@Positive Long id, ProfessorRequest request) {

	}

	public void deleteProfessor(@Positive Long id) {

	}

	public List<ProfessorResponse> getProfessorList() {
		return null;
	}
}

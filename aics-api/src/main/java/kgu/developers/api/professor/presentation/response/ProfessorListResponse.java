package kgu.developers.api.professor.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.professor.domain.Professor;
import lombok.Builder;

import java.util.List;

@Builder
public record ProfessorListResponse(
	@Schema(description = "교수 리스트",
		requiredMode = REQUIRED)
	List<ProfessorResponse> contents
) {
	public static ProfessorListResponse from(List<Professor> professors) {
		return ProfessorListResponse.builder()
			.contents(professors.stream()
				.map(ProfessorResponse::from)
				.toList())
			.build();
	}
}

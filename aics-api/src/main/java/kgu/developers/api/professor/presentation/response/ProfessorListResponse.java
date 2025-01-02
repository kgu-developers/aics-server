package kgu.developers.api.professor.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.professor.application.response.ProfessorResponse;
import kgu.developers.domain.professor.domain.Professor;
import lombok.Builder;

import java.util.List;

@Builder
public record ProfessorListResponse(
	@Schema(description = "교수 리스트",
		example = "[{"
			+ "\"id\": 1, "
			+ "\"name\": \"이은정\", "
			+ "\"officeLoc\": \"8213호\", "
			+ "\"contact\": \"031-249-9671\", "
			+ "\"email\": \"ejlee@kyonggi.ac.kr\", "
			+ "\"course\": \"프로그래밍언어론\"}]",
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

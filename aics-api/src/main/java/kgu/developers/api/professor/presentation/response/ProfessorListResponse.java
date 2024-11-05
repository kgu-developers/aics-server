package kgu.developers.api.professor.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.professor.domain.Professor;
import lombok.Builder;

import java.util.List;

@Builder
public record ProfessorListResponse(
	@Schema(description = "교수 리스트",
		example = "[{"
			+ "\"id\": 5, "
			+ "\"name\": \"홍길동\", "
			+ "\"officeLoc\": \"8000호\", "
			+ "\"contact\": \"010-0000-0001\", "
			+ "\"email\": \"prof@kyonggi.ac.kr\", "
			+ "\"course\": \"컴퓨터공학개론\"}]",
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

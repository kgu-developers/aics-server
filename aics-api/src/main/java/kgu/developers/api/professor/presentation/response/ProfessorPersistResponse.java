package kgu.developers.api.professor.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ProfessorPersistResponse(
	@Schema(description = "교수 id", example = "3", requiredMode = REQUIRED)
	Long professorId
) {
	public static ProfessorPersistResponse of(Long professorId) {
		return ProfessorPersistResponse.builder()
			.professorId(professorId)
			.build();
	}
}

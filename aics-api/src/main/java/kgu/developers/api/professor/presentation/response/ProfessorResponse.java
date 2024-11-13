package kgu.developers.api.professor.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import kgu.developers.domain.professor.domain.Professor;
import lombok.Builder;

@Builder
public record ProfessorResponse(
	@Schema(description = "교수 id", example = "1", requiredMode = REQUIRED)
	Long id,

	@Schema(description = "교수 이름", example = "이은정", requiredMode = REQUIRED)
	String name,

	@Schema(description = "연락처", example = "031-249-9671", requiredMode = REQUIRED)
	String contact,

	@Schema(description = "이메일", example = "ejlee@kyonggi.ac.kr", requiredMode = REQUIRED)
	String email
) {
	public static ProfessorResponse from(Professor professor) {
		return ProfessorResponse.builder()
			.id(professor.getId())
			.name(professor.getName())
			.contact(professor.getContact())
			.email(professor.getEmail())
			.build();
	}
}

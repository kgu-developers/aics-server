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

	@Schema(description = "사무실 위치", example = "8213호", requiredMode = REQUIRED)
	String officeLoc,

	@Schema(description = "연락처", example = "031-249-9671", requiredMode = REQUIRED)
	String contact,

	@Schema(description = "이메일", example = "ejlee@kyonggi.ac.kr", requiredMode = REQUIRED)
	String email,

	@Schema(description = "담당과목", example = "프로그래밍언어론", requiredMode = REQUIRED)
	String course
) {
	public static ProfessorResponse from(Professor professor) {
		return ProfessorResponse.builder()
			.id(professor.getId())
			.name(professor.getName())
			.officeLoc(professor.getOfficeLoc())
			.contact(professor.getContact())
			.email(professor.getEmail())
			.course(professor.getCourse())
			.build();
	}
}

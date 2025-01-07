package kgu.developers.domain.professor.application.response;

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

	@Schema(description = "직위", example = "조교수", requiredMode = REQUIRED)
	String type,

	@Schema(description = "연락처", example = "031-249-9671", requiredMode = REQUIRED)
	String contact,

	@Schema(description = "연구실 위치", example = "8213호", requiredMode = REQUIRED)
	String officeLoc,

	@Schema(description = "이메일", example = "ejlee@kyonggi.ac.kr", requiredMode = REQUIRED)
	String email,

	@Schema(description = "이미지 URL", example = "https://image.com/professor/profile/image", requiredMode = REQUIRED)
	String img
) {
	public static ProfessorResponse from(Professor professor) {
		return ProfessorResponse.builder()
			.id(professor.getId())
			.name(professor.getName())
			.type(professor.getRole().getDescription())
			.contact(professor.getContact())
			.officeLoc(professor.getOfficeLoc())
			.email(professor.getEmail())
			.img(professor.getImg())
			.build();
	}
}

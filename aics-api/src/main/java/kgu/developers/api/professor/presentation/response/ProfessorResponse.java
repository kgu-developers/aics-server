package kgu.developers.api.professor.presentation.response;

import kgu.developers.domain.professor.domain.Professor;
import lombok.Builder;

@Builder
public record ProfessorResponse(
	Long id,
	String name,
	String officeLoc,
	String contact,
	String email,
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

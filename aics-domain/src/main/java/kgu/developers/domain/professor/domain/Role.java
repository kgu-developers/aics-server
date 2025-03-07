package kgu.developers.domain.professor.domain;

import kgu.developers.domain.professor.exception.NoSuchRoleException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	PROFESSOR("교수"),
	ASSISTANT("조교수");

	private final String description;

	public static Role of(String description) {
		return switch (description) {
			case "교수" -> PROFESSOR;
			case "조교수" -> ASSISTANT;
			default -> throw new NoSuchRoleException();
		};
	}
}

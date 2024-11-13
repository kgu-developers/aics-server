package kgu.developers.domain.professor.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	PROFESSOR("교수"),
	ASSISTANT("조교수");

	private final String description;
}

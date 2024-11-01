package kgu.developers.domain.about.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MainCategory {
	DEPT_INTRO("학과소개"),
	EDU_ACTIVITIES("교육활동"),
	;

	private final String description;
}

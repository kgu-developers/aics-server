package kgu.developers.domain.about.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubCategory {
	// 학과소개
	DEPT_INTRO("학과소개"),
	HISTORY("연혁"),
	EDU_ENVIRONMENT("교육환경"),
	EDU_OBJECTIVES("교육목표"),

	// 교육활동
	CURRICULUM("교육과정"),
	LEARNING_ACTIVITIES("학습활동"),
	CLUB_INTRO("동아리소개"),
	;
	private final String description;
}

package kgu.developers.domain.about.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
	// 소개
	DEPT_INTRO("학부 소개"),
	DIRECTIONS("찾아오시는 길"),
	CLUB("동아리");

	private final String description;
}

package kgu.developers.core.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Major {

	// TODO Enum 네이밍 아래와 같이 했는데 어떤지 질문
	CSE("컴퓨터공학전공"),
	AIT("인공지능전공"),
	SSS("SW안전보안전공");

	private final String description;
}

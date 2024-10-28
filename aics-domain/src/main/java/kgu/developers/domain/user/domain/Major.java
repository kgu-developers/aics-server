package kgu.developers.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Major {

	CSE("컴퓨터공학전공"),
	AIT("인공지능전공"),
	SSS("SW안전보안전공");

	private final String description;
}

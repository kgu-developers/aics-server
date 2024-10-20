package kgu.developers.core.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Grade {

	FRESH("1학년"),
	SOPHOMORE("2학년"),
	JUNIOR("3학년"),
	SENIOR("4학년"),
	FIFTH("5학년");

	private final String description;
}

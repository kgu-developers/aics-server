package kgu.developers.core.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {

	MEN("남자"), WOMEN("여자");

	private final String description;
}

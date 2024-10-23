package kgu.developers.core.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

	USER("일반사용자"),
	ADMIN("관리자"),
	SUPER("최고관리자");

	private final String description;
}

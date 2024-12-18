package kgu.developers.domain.file.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileDomain {
	ABOUT("소개"),
	CAROUSEL("캐러셀"),
	NOTIFICATION("공지사항"),
	;

	private final String description;
}

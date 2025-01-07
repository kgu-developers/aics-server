package kgu.developers.domain.file.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileDomain {
	ABOUT("소개"),
	CAROUSEL("캐러셀"),
	POST("게시글"),
	LAB("연구실"),
	;

	private final String description;
}

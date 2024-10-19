package kgu.developers.core.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

	PROF("교수"), TA("조교"), POSTGRAD("대학원생"), STUDENT("학부생"), CONCURRENT("복전생"), GUEST("게스트"), ETC("기타");

	private final String description;
}

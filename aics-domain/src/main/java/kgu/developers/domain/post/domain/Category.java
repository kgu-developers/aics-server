package kgu.developers.domain.post.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    NOTIFICATION("공지사항"),
    NEWS("학과 소식"),
    GRADUATION("졸업")
    ;

    private final String description;
}

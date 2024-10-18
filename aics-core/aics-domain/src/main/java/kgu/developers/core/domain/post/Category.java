package kgu.developers.core.domain.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    DEPT_INFO("학과공지"),
    LESSON_INFO("수업공지"),
    EMPLOY_INFO("취업공지"),
    DEPT_NEWS("학과 소식"),
    GOOD_WORKS("우수 작품전"),
    AWARDED("수상 소식")
    ;

    private final String description;

}

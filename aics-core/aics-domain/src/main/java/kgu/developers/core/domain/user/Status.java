package kgu.developers.core.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

    INSCHOOL("재학중"),
    EXPELLED("퇴학"),
    LEAVED("휴학"),
    ETC("기타")
    ;

    private final String description;
}

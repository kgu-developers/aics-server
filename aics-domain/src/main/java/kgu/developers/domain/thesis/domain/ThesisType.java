package kgu.developers.domain.thesis.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ThesisType {
    MID_THESIS("중간 보고서"),
    FINAL_THESIS("최종 보고서"),
    ;

    private final String description;
}


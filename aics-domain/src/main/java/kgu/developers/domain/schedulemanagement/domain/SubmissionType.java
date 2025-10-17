package kgu.developers.domain.schedulemanagement.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubmissionType {
    MIDTHESIS("중간논문"),
    FINALTHESIS("최종논문"),
    CERTIFICATE("자격증")
    ;
    private final String label;
}

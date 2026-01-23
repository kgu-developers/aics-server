package kgu.developers.domain.schedule.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubmissionType {
    SUBMITTED("신청접수"),
    MIDTHESIS("중간보고서"),
    FINALTHESIS("최종보고서"),
    CERTIFICATE("자격증"),
    APPROVED("최종 통과"),
    OTHER("기타")
    ;
    private final String label;
}

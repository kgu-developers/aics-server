package kgu.developers.api.graduationUser.presentation.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GraduationUserStatus {
    GRADUATION_TYPE_NOT_SUBMITTED("졸업 요건 취득 방식을 지정하지 않았어요"),
    PROFESSOR_NOT_ASSIGNED("아직 담당 교수 배정이 되지 않았어요"),
    MID_THESIS_NOT_SUBMITTED("중간 보고서를 제출하지 않았어요"),
    FINAL_THESIS_NOT_SUBMITTED("최종 보고서를 제출하지 않았어요"),
    CERTIFICATE_NOT_SUBMITTED("자격증 증빙 문서를 제출하지 않았어요"),
    GRADUATION_REQUIREMENTS_MET("졸업 요건을 모두 충족했어요"),
    ;

    private final String description;
}

package kgu.developers.domain.graduationUser.infrastructure.excel;

import kgu.developers.domain.graduationUser.domain.GraduationUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum GraduationUserExcelColumn {
    STUDENT_ID("학번", GraduationUser::getUserId,4000),
    NAME("이름", GraduationUser::getName,4000),
    DEPARTMENT("학과", GraduationUser::getDepartment,5000),
    GRADUATION_DATE("졸업 날짜", GraduationUser::getGraduationDate,4000),
    GRADUATION_TYPE("졸업 유형", GraduationUser::getGraduationType,4000),
    ADVISOR("지도교수", GraduationUser::getAdvisorProfessor,4000);

    private final String headerName;
    private final Function<GraduationUser, Object> valueExtractor;
    private final int width;
}

package kgu.developers.domain.graduationUser.infrastructure.excel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum GraduationUserExcelColumn {
    STUDENT_ID("학번", GraduationUserExcelRow::userId,4000),
    NAME("이름", GraduationUserExcelRow::name,4000),
    DEPARTMENT("학과", GraduationUserExcelRow::department,5000),
    GRADUATION_DATE("졸업 날짜", GraduationUserExcelRow::graduationDate,4000),
    GRADUATION_TYPE("졸업 유형", GraduationUserExcelRow::graduationType,4000),
    ADVISOR("지도교수", GraduationUserExcelRow::advisorProfessor,4000),
    CURRENT_STAGE("단계", GraduationUserExcelRow::currentStage, 5000),
    APPROVAL_STATUS("상태", GraduationUserExcelRow::approvalStatus, 4000),
    ;

    private final String headerName;
    private final Function<GraduationUserExcelRow, Object> valueExtractor;
    private final int width;
}

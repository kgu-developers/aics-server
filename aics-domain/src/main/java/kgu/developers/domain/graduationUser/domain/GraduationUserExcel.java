package kgu.developers.domain.graduationUser.domain;

import kgu.developers.domain.graduationUser.infrastructure.excel.GraduationUserExcelRow;

import java.util.List;

public interface GraduationUserExcel {
    byte[] generate(List<GraduationUserExcelRow> graduationUsers);
}

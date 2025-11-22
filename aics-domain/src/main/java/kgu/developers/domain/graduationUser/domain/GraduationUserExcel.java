package kgu.developers.domain.graduationUser.domain;

import java.util.List;

public interface GraduationUserExcel {
    byte[] generate(List<GraduationUser> graduationUsers);
}

package kgu.developers.domain.graduationUser.domain;

import java.util.Optional;

public interface GraduationUserRepository {
    GraduationUser save(GraduationUser graduationUser);

    Optional<GraduationUser> findByIdAndDeletedAtIsNull(Long graduationUserId);
}

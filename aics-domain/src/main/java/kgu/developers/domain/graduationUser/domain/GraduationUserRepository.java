package kgu.developers.domain.graduationUser.domain;

import kgu.developers.common.response.PaginatedListResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GraduationUserRepository {
    GraduationUser save(GraduationUser graduationUser);

    Optional<GraduationUser> findByIdAndDeletedAtIsNull(Long graduationUserId);

    PaginatedListResponse<GraduationUser> findAllByNameAndGraduationTypeOrderByIdAsc(Pageable pageable, String name, GraduationType graduationType);

    List<GraduationUser> findAllByGraduationTypeOrderByIdAsc(GraduationType graduationType);

    Optional<GraduationUser> findByUserIdAndDeletedAtIsNull(String id);
}

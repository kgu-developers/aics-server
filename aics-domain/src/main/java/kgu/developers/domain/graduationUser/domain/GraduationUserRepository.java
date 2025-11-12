package kgu.developers.domain.graduationUser.domain;

import kgu.developers.common.response.PaginatedListResponse;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface GraduationUserRepository {
    GraduationUser save(GraduationUser graduationUser);

    Optional<GraduationUser> findByIdAndDeletedAtIsNull(Long graduationUserId);

    PaginatedListResponse<GraduationUser> findAllByNameAndGraduationTypeOrderByIdDesc(PageRequest pageable, String name, GraduationType graduationType);
}

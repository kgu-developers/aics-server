package kgu.developers.domain.graduationUser.application.query;

import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.domain.GraduationUserRepository;
import kgu.developers.domain.graduationUser.exception.GraduationUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GraduationUserQueryService {
    private final GraduationUserRepository graduationUserRepository;

    public GraduationUser getById(Long graduationUserId) {
        return graduationUserRepository.findByIdAndDeletedAtIsNull(graduationUserId)
            .orElseThrow(GraduationUserNotFoundException::new);
    }

    public PaginatedListResponse<GraduationUser> getUsersByNameAndGraduationType(PageRequest pageable, String name, GraduationType graduationType) {
        return graduationUserRepository.findAllByNameAndGraduationTypeOrderByIdDesc(pageable,name, graduationType);
    }
}

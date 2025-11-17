package kgu.developers.domain.graduationUser.infrastructure.repository;

import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.domain.GraduationUserRepository;
import kgu.developers.domain.graduationUser.infrastructure.entity.GraduationUserJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GraduationUserRepositoryImpl implements GraduationUserRepository {

    private final JpaGraduationUserRepository jpaGraduationUserRepository;
    private final QueryGraduationUserRepository queryGraduationUserRepository;

    @Override
    public GraduationUser save(GraduationUser graduationUser) {
        GraduationUserJpaEntity entity = GraduationUserJpaEntity.toEntity(graduationUser);
        GraduationUserJpaEntity savedEntity = jpaGraduationUserRepository.save(entity);
        return GraduationUserJpaEntity.toDomain(savedEntity);
    }

    @Override
    public Optional<GraduationUser> findByIdAndDeletedAtIsNull(Long graduationUserId) {
        return jpaGraduationUserRepository.findByIdAndDeletedAtIsNull(graduationUserId)
            .map(GraduationUserJpaEntity::toDomain);
    }

    @Override
    public PaginatedListResponse<GraduationUser> findAllByNameAndGraduationTypeOrderByIdAsc(Pageable pageable, String name, GraduationType graduationType) {
        return queryGraduationUserRepository.findAllByNameAndGraduationTypeOrderByIdAsc(pageable,name,graduationType);
    }

    @Override
    public List<GraduationUser> findAllByGraduationTypeOrderByIdAsc(GraduationType graduationType) {
        return queryGraduationUserRepository.findAllByGraduationTypeOrderByIdAsc(graduationType);
    }

    @Override
    public Optional<GraduationUser> findByUserIdAndDeletedAtIsNull(String userId) {
        return jpaGraduationUserRepository.findByUserIdAndDeletedAtIsNull(userId)
            .map(GraduationUserJpaEntity::toDomain);
    }
}

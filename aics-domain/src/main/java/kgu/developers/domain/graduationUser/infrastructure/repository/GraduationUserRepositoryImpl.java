package kgu.developers.domain.graduationUser.infrastructure.repository;

import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.domain.GraduationUserRepository;
import kgu.developers.domain.graduationUser.infrastructure.entity.GraduationUserJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GraduationUserRepositoryImpl implements GraduationUserRepository {

    private final JpaGraduationUserRepository jpaGraduationUserRepository;

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
}

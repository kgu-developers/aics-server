package kgu.developers.domain.graduationUser.infrastructure.repository;

import kgu.developers.domain.graduationUser.infrastructure.entity.GraduationUserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaGraduationUserRepository extends JpaRepository<GraduationUserJpaEntity, Long> {

    Optional<GraduationUserJpaEntity> findByIdAndDeletedAtIsNull(Long graduationUserId);

    Optional<GraduationUserJpaEntity> findByUserIdAndDeletedAtIsNull(String userId);
}

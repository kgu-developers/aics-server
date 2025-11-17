package kgu.developers.domain.graduationUser.infrastructure.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kgu.developers.common.response.PageableResponse;
import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import kgu.developers.domain.graduationUser.domain.GraduationUser;
import kgu.developers.domain.graduationUser.infrastructure.entity.GraduationUserJpaEntity;
import kgu.developers.domain.graduationUser.infrastructure.entity.QGraduationUserJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QueryGraduationUserRepository {
    private final JPAQueryFactory queryFactory;

    public PaginatedListResponse<GraduationUser> findAllByNameAndGraduationTypeOrderByIdAsc(Pageable pageable, String name, GraduationType graduationType) {
        QGraduationUserJpaEntity graduationUser = QGraduationUserJpaEntity.graduationUserJpaEntity;

        BooleanExpression whereClause = graduationUser.deletedAt.isNull()
            .and(name != null ? graduationUser.name.like(name) : null)
            .and(graduationType != null ? graduationUser.graduationType.eq(graduationType) : null);

        List<GraduationUserJpaEntity> graduationUserEntities = queryFactory.select(graduationUser)
            .from(graduationUser)
            .where(whereClause)
            .orderBy(graduationUser.id.asc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        List<GraduationUser> graduationUsers = graduationUserEntities.stream()
            .map(GraduationUserJpaEntity::toDomain)
            .toList();

        List<Long> graduationUsersIds = queryFactory.select(graduationUser.id)
            .from(graduationUser)
            .where(whereClause)
            .orderBy(graduationUser.id.desc())
            .fetch();

        return PaginatedListResponse.of(graduationUsers, PageableResponse.of(pageable, graduationUsersIds));

    }

    public List<GraduationUser> findAllByGraduationTypeOrderByIdAsc(GraduationType graduationType) {
        QGraduationUserJpaEntity graduationUser = QGraduationUserJpaEntity.graduationUserJpaEntity;

        BooleanExpression whereClause = graduationUser.deletedAt.isNull()
            .and(graduationType != null ? graduationUser.graduationType.eq(graduationType) : null);

        List<GraduationUserJpaEntity> graduationUserEntities = queryFactory.select(graduationUser)
            .from(graduationUser)
            .where(whereClause)
            .orderBy(graduationUser.id.asc())
            .fetch();

        return graduationUserEntities.stream()
            .map(GraduationUserJpaEntity::toDomain)
            .toList();
    }
}

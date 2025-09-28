package kgu.developers.domain.file.infrastructure.repository;

import static kgu.developers.domain.file.infrastructure.entity.QFileJpaEntity.fileJpaEntity;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryFileRepository {
	private final JPAQueryFactory queryFactory;

	public Optional<String> findPhysicalPathById(Long id) {
		return Optional.ofNullable(id)
			.map(i -> queryFactory
				.select(fileJpaEntity.physicalPath)
				.from(fileJpaEntity)
				.where(fileJpaEntity.id.eq(i))
				.fetchOne()
			);
	}
}

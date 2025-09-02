package kgu.developers.domain.file.infrastructure;

import static kgu.developers.domain.file.domain.QFileEntity.fileEntity;

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
				.select(fileEntity.physicalPath)
				.from(fileEntity)
				.where(fileEntity.id.eq(i))
				.fetchOne()
			);
	}
}

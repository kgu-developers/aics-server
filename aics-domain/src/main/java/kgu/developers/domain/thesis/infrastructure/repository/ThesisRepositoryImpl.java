package kgu.developers.domain.thesis.infrastructure.repository;

import org.springframework.stereotype.Repository;

import kgu.developers.domain.thesis.domain.Thesis;
import kgu.developers.domain.thesis.domain.ThesisRepository;
import kgu.developers.domain.thesis.infrastructure.entity.ThesisJpaEntity;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ThesisRepositoryImpl implements ThesisRepository {
	private final JpaThesisRepository jpaThesisRepository;

	@Override
	public Long save(Thesis thesis) {
		return jpaThesisRepository.save(ThesisJpaEntity.toEntity(thesis)).getId();
	}
}

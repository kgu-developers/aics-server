package kgu.developers.domain.file.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import kgu.developers.domain.file.infrastructure.entity.FileJpaEntity;

public interface JpaFileRepository extends JpaRepository<FileJpaEntity, Long> {
}

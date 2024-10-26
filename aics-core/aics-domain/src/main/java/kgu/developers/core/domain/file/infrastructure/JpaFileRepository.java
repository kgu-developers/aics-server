package kgu.developers.core.domain.file.infrastructure;

import kgu.developers.core.domain.file.domain.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFileRepository extends JpaRepository<FileEntity, Long> {
}

package kgu.developers.domain.file.infrastructure;

import kgu.developers.domain.file.domain.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFileRepository extends JpaRepository<FileEntity, Long> {
}

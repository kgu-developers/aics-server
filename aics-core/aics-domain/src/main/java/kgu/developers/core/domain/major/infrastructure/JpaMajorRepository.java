package kgu.developers.core.domain.major.infrastructure;

import kgu.developers.core.domain.major.domain.Major;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMajorRepository extends JpaRepository<Major, Long> {
    Optional<Major> findByName(String majorName);
}

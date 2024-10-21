package kgu.developers.core.domain.major.infrastructure;

import kgu.developers.core.domain.major.domain.Major;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMajorRepository extends JpaRepository<Major, Long> {
    boolean existsByName(String majorName);
}

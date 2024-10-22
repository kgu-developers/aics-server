package kgu.developers.core.domain.major.infrastructure;

import java.util.Optional;

import kgu.developers.core.domain.major.domain.Major;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMajorRepository extends JpaRepository<Major, Long> {
    boolean existsByName(String majorName);

    Optional<Major> findByName(String name);
}

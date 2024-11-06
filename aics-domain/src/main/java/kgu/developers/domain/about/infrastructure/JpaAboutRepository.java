package kgu.developers.domain.about.infrastructure;

import kgu.developers.domain.about.domain.About;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAboutRepository extends JpaRepository<About, Long> {
}

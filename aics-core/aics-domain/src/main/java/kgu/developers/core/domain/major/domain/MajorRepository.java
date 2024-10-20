package kgu.developers.core.domain.major.domain;

import java.util.Optional;

public interface MajorRepository {
    Major save(Major major);

    Optional<Major> findByName(String s);
}

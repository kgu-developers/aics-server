package kgu.developers.core.domain.major.domain;

import java.util.Optional;

public interface MajorRepository {
    Major save(Major major);

    boolean existByName(String s);

    Optional<Major> findByName(String name);
}

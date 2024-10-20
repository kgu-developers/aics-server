package kgu.developers.core.domain.major.domain;

public interface MajorRepository {
    Major save(Major major);

    boolean existByName(String s);
}

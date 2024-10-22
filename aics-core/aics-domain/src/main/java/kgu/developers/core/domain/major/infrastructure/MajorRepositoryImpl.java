package kgu.developers.core.domain.major.infrastructure;

import java.util.Optional;

import kgu.developers.core.domain.major.domain.Major;
import kgu.developers.core.domain.major.domain.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MajorRepositoryImpl implements MajorRepository {
    private final JpaMajorRepository jpaMajorRepository;

    @Override
    public Major save(Major major) {
        return jpaMajorRepository.save(major);
    }

    @Override
    public boolean existByName(String majorName) {
        return jpaMajorRepository.existsByName(majorName);
    }

    @Override
    public Optional<Major> findByName(String name) {
        return jpaMajorRepository.findByName(name);
    }
}

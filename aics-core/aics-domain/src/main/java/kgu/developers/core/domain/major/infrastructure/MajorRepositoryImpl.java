package kgu.developers.core.domain.major.infrastructure;

import kgu.developers.core.domain.major.domain.Major;
import kgu.developers.core.domain.major.domain.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MajorRepositoryImpl implements MajorRepository {
    private final JpaMajorRepository jpaMajorRepository;

    @Override
    public Major save(Major major) {
        return jpaMajorRepository.save(major);
    }

    @Override
    public Optional<Major> findByMajorName(String majorName) {
        return jpaMajorRepository.findByMajorName(majorName);
    }
}

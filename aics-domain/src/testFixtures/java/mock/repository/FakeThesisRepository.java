package mock.repository;

import kgu.developers.domain.thesis.domain.Thesis;
import kgu.developers.domain.thesis.domain.ThesisRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FakeThesisRepository implements ThesisRepository {
    private final List<Thesis> data = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong sequence = new AtomicLong(1);
    @Override
    public Long save(Thesis thesis) {
        Thesis savedCertificate = Thesis.builder()
            .id(sequence.getAndIncrement())
            .thesisFileId(thesis.getScheduleId())
            .approval(thesis.isApproval())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deletedAt(null)
            .build();

        data.add(savedCertificate);
        return savedCertificate.getId();
    }

    @Override
    public Optional<Thesis> findByIdAndDeletedAtIsNull(Long id) {
        return data.stream()
            .filter(thesis -> thesis.getId().equals(id))
            .filter(thesis -> thesis.getDeletedAt() == null)
            .findFirst();
    }

    @Override
    public Optional<Boolean> findApprovalByIdAndDeletedAtIsNull(Long id) {
        return data.stream()
            .filter(thesis -> thesis.getId().equals(id))
            .filter(thesis -> thesis.getDeletedAt() == null)
            .findFirst()
            .map(Thesis::isApproval);
    }
}

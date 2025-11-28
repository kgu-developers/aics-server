package mock.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import kgu.developers.domain.schedule.domain.Schedule;
import kgu.developers.domain.schedule.domain.ScheduleRepository;
import kgu.developers.domain.schedule.domain.SubmissionType;
import kgu.developers.domain.schedule.infrastructure.entity.ScheduleJpaEntity;

public class FakeScheduleRepository implements ScheduleRepository {
	private final List<ScheduleJpaEntity> data = Collections.synchronizedList(new ArrayList<>());
	private final AtomicLong sequence = new AtomicLong(1);

	@Override
	public Schedule save(Schedule schedule) {
		Long id = schedule.getId() == null ? sequence.getAndIncrement() : schedule.getId();

		ScheduleJpaEntity entity = ScheduleJpaEntity.builder()
			.id(id)
			.submissionType(schedule.getSubmissionType())
			.content(schedule.getContent())
			.startDate(schedule.getStartDate())
			.endDate(schedule.getEndDate())
			.build();

		data.removeIf(item -> item.getId().equals(id));
		data.add(entity);
		return entity.toDomain();
	}

	@Override
	public void deleteById(Long id) {
		data.removeIf(schedule -> schedule.getId().equals(id));
	}

	@Override
	public Optional<Schedule> findById(Long id) {
		return data.stream()
				.filter(schedule -> schedule.getId().equals(id))
				.findFirst()
				.map(ScheduleJpaEntity::toDomain);
	}

	@Override
	public List<Schedule> findAll() {
		return data.stream()
				.map(ScheduleJpaEntity :: toDomain)
				.toList();
	}

	@Override
	public Optional<Schedule> findBySubmissionType(SubmissionType submissionType) {
		return data.stream()
				.filter(schedule -> schedule.getSubmissionType()== submissionType)
				.findFirst()
				.map(ScheduleJpaEntity :: toDomain);
	}

	@Override
	public boolean existsById(Long id) {
		return data.stream().anyMatch(schedule -> schedule.getId().equals(id));
	}
}

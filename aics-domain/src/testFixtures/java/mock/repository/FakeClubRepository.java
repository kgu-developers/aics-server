package mock.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import kgu.developers.domain.club.domain.Club;
import kgu.developers.domain.club.domain.ClubRepository;
import kgu.developers.domain.club.infrastructure.ClubJpaEntity;

public class FakeClubRepository implements ClubRepository {
	private final List<ClubJpaEntity> data = Collections.synchronizedList(new ArrayList<>());
	private final AtomicLong sequence = new AtomicLong(1);

	@Override
	public Club save(Club club) {
		ClubJpaEntity newEntity = ClubJpaEntity.builder()
				.id(club.getId() == null ? sequence.getAndIncrement() : club.getId())
				.name(club.getName())
				.description(club.getDescription())
				.site(club.getSite())
				.fileId(club.getFileId())
				.build();

		if (club.getId() != null) {
			data.removeIf(entity -> entity.getId().equals(club.getId()));
		}
		data.add(newEntity);
		return newEntity.toDomain();

	}

	@Override
	public List<Club> findAll() {
		return data.stream()
				.map(ClubJpaEntity::toDomain)
				.toList();
	}

	@Override
	public Optional<Club> findById(Long id) {
		return data.stream()
			.filter(club -> club.getId().equals(id))
			.findFirst()
				.map(ClubJpaEntity::toDomain);
	}

	@Override
	public void deleteById(Long id) {
		data.removeIf(club -> club.getId().equals(id));
	}
}

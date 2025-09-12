package kgu.developers.domain.club.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import kgu.developers.domain.club.domain.Club;
import kgu.developers.domain.club.domain.ClubRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ClubRepositoryImpl implements ClubRepository {
	private final JpaClubRepository jpaClubRepository;

	@Override
	public Club save(Club club) {
		ClubEntity entity = ClubEntity.fromDomain(club);
		ClubEntity savedEntity = jpaClubRepository.save(entity);

		return savedEntity.toDomain();
	}

	@Override
	public List<Club> findAll() {
		List<ClubEntity> entities = jpaClubRepository.findAll();
		return entities.stream()
				.map(ClubEntity::toDomain)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Club> findById(Long id) {
		Optional<ClubEntity> optionalEntity = jpaClubRepository.findById(id);
		return optionalEntity.map(ClubEntity::toDomain);
	}

	@Override
	public void deleteById(Long id) {
		jpaClubRepository.deleteById(id);
	}
}

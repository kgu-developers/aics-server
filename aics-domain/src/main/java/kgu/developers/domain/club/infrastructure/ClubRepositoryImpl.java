package kgu.developers.domain.club.infrastructure;

import java.util.List;
import java.util.Optional;

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
		return jpaClubRepository.save(club);
	}

	@Override
	public List<Club> findAll() {
		return jpaClubRepository.findAll();
	}

	@Override
	public Optional<Club> findById(Long id) {
		return jpaClubRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		jpaClubRepository.deleteById(id);
	}
}

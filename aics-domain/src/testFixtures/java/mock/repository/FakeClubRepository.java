package mock.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import kgu.developers.domain.club.domain.Club;
import kgu.developers.domain.club.domain.ClubRepository;

public class FakeClubRepository implements ClubRepository {
	private final List<Club> data = Collections.synchronizedList(new ArrayList<>());
	private final AtomicLong sequence = new AtomicLong(1);

	@Override
	public Club save(Club club) {
		Club newClub = Club.builder()
			.id(sequence.getAndIncrement())
			.name(club.getName())
			.description(club.getDescription())
			.site(club.getSite())
			.build();

		data.add(newClub);
		return newClub;
	}

	@Override
	public List<Club> findAll() {
		return List.copyOf(data);
	}

	@Override
	public Optional<Club> findById(Long id) {
		return data.stream()
			.filter(club -> club.getId().equals(id))
			.findFirst();
	}

	@Override
	public void deleteById(Long id) {
		data.removeIf(club -> club.getId().equals(id));
	}
}

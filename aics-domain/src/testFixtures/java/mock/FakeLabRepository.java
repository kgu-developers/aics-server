package mock;

import kgu.developers.domain.lab.domain.Lab;
import kgu.developers.domain.lab.domain.LabRepository;

import java.util.List;
import java.util.Optional;

// todo
public class FakeLabRepository implements LabRepository {
	@Override
	public Lab save(Lab lab) {
		return null;
	}

	@Override
	public Optional<Lab> findById(Long id) {
		return Optional.empty();
	}

	@Override
	public List<Lab> findAllByOrderByName() {
		return List.of();
	}

	@Override
	public void delete(Lab lab) {

	}
}

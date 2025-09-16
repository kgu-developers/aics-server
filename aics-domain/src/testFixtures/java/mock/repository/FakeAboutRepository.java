package mock.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.AboutRepository;
import kgu.developers.domain.about.domain.Category;
import kgu.developers.domain.about.infrastructure.AboutJpaEntity;

public class FakeAboutRepository implements AboutRepository {
	private final List<AboutJpaEntity> data = Collections.synchronizedList(new ArrayList<>());
	private final AtomicLong sequence = new AtomicLong(1);

	@Override
	public About save(About about) {
		AboutJpaEntity newEntity;
		newEntity = AboutJpaEntity.builder()
				.id(about.getId() == null ? sequence.getAndIncrement() : about.getId())
				.content(about.getContent())
				.category(about.getCategory())
				.build();
		if(about.getId() != null) {
			data.removeIf(entity -> entity.getId().equals(about.getId()));
		}
		data.add(newEntity);
		return newEntity.toDomain();
	}

	@Override
	public Optional<About> findByCategory(Category category) {
		return data.stream()
			.filter(about -> about.getCategory().equals(category))
			.findFirst()
				.map(AboutJpaEntity::toDomain);
	}

	@Override
	public Optional<About> findById(Long id) {
		return data.stream()
				.filter(e -> e.getId().equals(id))
				.findFirst()
				.map(AboutJpaEntity::toDomain);
	}
}

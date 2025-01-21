package mock.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import kgu.developers.domain.carousel.domain.Carousel;
import kgu.developers.domain.carousel.domain.CarouselRepository;
import mock.TestEntityUtils;

public class FakeCarouselRepository implements CarouselRepository {
	private final List<Carousel> data = Collections.synchronizedList(new ArrayList<>());
	private final AtomicLong sequence = new AtomicLong(1);

	@Override
	public Carousel save(Carousel carousel) {
		Carousel savedCarousel = Carousel.builder()
			.id(sequence.getAndIncrement())
			.text(carousel.getText())
			.link(carousel.getLink())
			.file(carousel.getFile())
			.build();

		TestEntityUtils.setCreatedAt(savedCarousel, LocalDateTime.now());

		data.add(savedCarousel);
		return savedCarousel;
	}

	@Override
	public void deleteById(Long id) {
		data.removeIf(carousel -> carousel.getId().equals(id));
	}

	@Override
	public List<Carousel> findAllByFileIsNotNullOrderByCreatedAtDesc() {
		return data.stream()
			.filter(carousel -> carousel.getFile() != null)
			.sorted((c1, c2) -> c2.getCreatedAt().compareTo(c1.getCreatedAt()))
			.toList();
	}
}

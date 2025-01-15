package mock.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.AboutRepository;
import kgu.developers.domain.about.domain.MainCategory;
import kgu.developers.domain.about.domain.SubCategory;
import mock.TestEntityUtils;

public class FakeAboutRepository implements AboutRepository {
	private final List<About> data = Collections.synchronizedList(new ArrayList<>());
	private final AtomicLong sequence = new AtomicLong(1);

	@Override
	public About save(About about) {
		About newAbout = About.builder()
			.id(sequence.getAndIncrement())
			.mainCategory(about.getMainCategory())
			.subCategory(about.getSubCategory())
			.detailCategory(about.getDetailCategory())
			.content(about.getContent())
			.build();

		TestEntityUtils.setCreatedAt(newAbout, LocalDateTime.now());

		data.add(newAbout);
		return newAbout;
	}

	@Override
	public Optional<About> findByMainAndSubAndDetail(MainCategory main, SubCategory sub, String detail) {
		return data.stream()
			.filter(about -> about.getMainCategory().equals(main)
				&& about.getSubCategory().equals(sub)
				&& about.getDetailCategory().equals(detail))
			.findFirst();
	}

	@Override
	public Optional<About> findByMainAndSub(MainCategory main, SubCategory sub) {
		return data.stream()
			.filter(about -> about.getMainCategory().equals(main)
				&& about.getSubCategory().equals(sub))
			.findFirst();
	}

	@Override
	public Optional<About> findById(Long id) {
		return data.stream()
			.filter(about -> about.getId().equals(id))
			.findFirst();
	}
}

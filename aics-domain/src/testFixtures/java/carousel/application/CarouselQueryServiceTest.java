package carousel.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.carousel.application.query.CarouselQueryService;
import kgu.developers.domain.carousel.domain.Carousel;
import mock.repository.FakeCarouselRepository;

public class CarouselQueryServiceTest {
	private CarouselQueryService carouselQueryService;
	private static Carousel carousel;

	private static final Long FAKE_FILE_ID = 1L;

	@BeforeEach
	public void init() {
		FakeCarouselRepository fakeCarouselRepository = new FakeCarouselRepository();
		carouselQueryService = new CarouselQueryService(fakeCarouselRepository);
		saveTestCarousel(fakeCarouselRepository);
	}

	private static void saveTestCarousel(FakeCarouselRepository fakeCarouselRepository) {
		carousel = fakeCarouselRepository.save(
			Carousel.create("컴퓨터공학부 홈페이지 메인 이미지", "https://kgu.ac.kr", FAKE_FILE_ID)
		);

		fakeCarouselRepository.save(
			Carousel.create("SW 중심대학 선정", "https://kgu.ac.kr", FAKE_FILE_ID)
		);
	}

	@Test
	@DisplayName("getAllCarousels 메서드는 Carousel 목록을 반환한다.")
	public void getAllCarousels_success() {
		//given
		//when
		List<Carousel> contents = carouselQueryService.getAllCarousels();

		//then
		assertEquals(2, contents.size());
		assertEquals("SW 중심대학 선정", contents.get(0).getText());
		assertEquals("https://kgu.ac.kr", contents.get(0).getLink());
		assertEquals("컴퓨터공학부 홈페이지 메인 이미지", contents.get(1).getText());
		assertEquals("https://kgu.ac.kr", contents.get(1).getLink());
	}

	@Test
	@DisplayName("getById는 해당 캐러셀을 조회한다.")
	public void getById_success() {
		// given
		Long id = 1L;

		// when
		Carousel result = carouselQueryService.getById(id);

		// then
		assertEquals(carousel, result);
	}
}

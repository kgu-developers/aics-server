package carousel.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.api.carousel.application.CarouselFacade;
import kgu.developers.api.carousel.presentation.response.CarouselListResponse;
import kgu.developers.domain.carousel.application.query.CarouselQueryService;
import kgu.developers.domain.carousel.domain.Carousel;
import kgu.developers.domain.file.domain.FileEntity;
import mock.repository.FakeCarouselRepository;

public class CarouselFacadeTest {
	private CarouselFacade carouselFacade;

	private static final int TARGET_CAROUSEL_SIZE = 2;

	@BeforeEach
	public void init() {
		initializeCarouselFacade();
	}

	private void initializeCarouselFacade() {
		FakeCarouselRepository fakeCarouselRepository = new FakeCarouselRepository();
		carouselFacade = new CarouselFacade(
			new CarouselQueryService(fakeCarouselRepository)
		);
		saveTestCarousel(fakeCarouselRepository);
	}

	private static void saveTestCarousel(FakeCarouselRepository fakeCarouselRepository) {
		fakeCarouselRepository.save(
			Carousel.create("컴퓨터공학부 홈페이지 메인 이미지", "https://kgu.ac.kr", FileEntity.builder().build())
		);

		fakeCarouselRepository.save(
			Carousel.create("SW 중심대학 선정", "https://kgu.ac.kr", FileEntity.builder().build())
		);
	}

	@Test
	@DisplayName("getCarousels은 Carousel 목록을 반환한다")
	public void getCarousels_Success() {
		// given
		// when
		CarouselListResponse response = carouselFacade.getCarousels();

		// then
		assertEquals(TARGET_CAROUSEL_SIZE, response.contents().size());
		assertEquals("SW 중심대학 선정", response.contents().get(0).text());
		assertEquals("https://kgu.ac.kr", response.contents().get(0).link());
		assertEquals("컴퓨터공학부 홈페이지 메인 이미지", response.contents().get(1).text());
		assertEquals("https://kgu.ac.kr", response.contents().get(1).link());

	}
}

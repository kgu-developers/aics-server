package carousel.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.carousel.application.command.CarouselCommandService;
import kgu.developers.domain.carousel.domain.Carousel;
import kgu.developers.domain.file.application.query.FileQueryService;
import kgu.developers.domain.file.domain.FileEntity;
import mock.repository.FakeCarouselRepository;
import mock.repository.FakeFileRepository;

public class CarouselCommandServiceTest {
	private CarouselCommandService carouselCommandService;

	private static final Long TEST_FILE_ID = 1L;
	private static final Long SAVE_TARGET_ID = 1L;

	@BeforeEach
	public void init() {
		initializeCarouselCommandService();
	}

	private void initializeCarouselCommandService() {
		FakeFileRepository fakeFileRepository = new FakeFileRepository();
		carouselCommandService = new CarouselCommandService(
			new FileQueryService(fakeFileRepository),
			new FakeCarouselRepository()
		);
		saveTestFile(fakeFileRepository);
	}

	private static void saveTestFile(FakeFileRepository fakeFileRepository) {
		fakeFileRepository.save(
			FileEntity.create(
				"경기대학교 AI컴퓨터공학부 메인 이미지",
				"/files/carousel/main_image.jpg",
				1234L,
				"image/jpeg"
			)
		);
	}

	@Test
	@DisplayName("createCarousel 메서드는 이미지 파일을 저장하고 Carousel을 생성한다")
	public void createCarousel_success() {
		// given
		String targetText = "경기대학교 AI컴퓨터공학부 메인 이미지";
		String targetLink = "https://www.kgu.ac.kr/";

		// when
		Long savedCarouselId = carouselCommandService.createCarousel(TEST_FILE_ID, targetText, targetLink);

		// then
		assertEquals(SAVE_TARGET_ID, savedCarouselId);
	}

	@Test
	@DisplayName("upateCarousel은 캐러셀을 수정한다.")
	public void updateCarousel_success() {
		Carousel carousel = Carousel.builder().build();

		String link = "https://www.new.kgu.ac.kr/";
		String text = "newText";

		// when
		carouselCommandService.updateCarousel(carousel, link, text, null);

		// then
		assertEquals(text, carousel.getText());
		assertEquals(link, carousel.getLink());
		assertNull(carousel.getFileId());
	}
}

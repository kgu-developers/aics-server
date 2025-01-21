package carousel.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.admin.carousel.application.CarouselAdminFacade;
import kgu.developers.admin.carousel.presentation.request.CarouselRequest;
import kgu.developers.admin.carousel.presentation.response.CarouselPersistResponse;
import kgu.developers.domain.carousel.application.command.CarouselCommandService;
import kgu.developers.domain.file.application.query.FileQueryService;
import kgu.developers.domain.file.domain.FileEntity;
import mock.repository.FakeCarouselRepository;
import mock.repository.FakeFileRepository;

public class CarouselAdminFacadeTest {
	private CarouselAdminFacade carouselAdminFacade;

	private static final Long TEST_FILE_ID = 1L;
	private static final Long SAVE_TARGET_ID = 1L;

	@BeforeEach
	public void init() {
		initializeCarouselAdminFacade();
	}

	private void initializeCarouselAdminFacade() {
		FakeFileRepository fakeFileRepository = new FakeFileRepository();
		carouselAdminFacade = new CarouselAdminFacade(
			new CarouselCommandService(
				new FileQueryService(fakeFileRepository),
				new FakeCarouselRepository()
			)
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
	@DisplayName("createCarousel은 Carousel을 생성한다")
	public void createCarousel_Success() {
		// given
		CarouselRequest request = new CarouselRequest("경기대학교 AI컴퓨터공학부 메인 이미지", "https://www.kgu.ac.kr/");

		// when
		CarouselPersistResponse response = carouselAdminFacade.createCarousel(TEST_FILE_ID, request);

		// then
		assertEquals(SAVE_TARGET_ID, response.id());
	}
}

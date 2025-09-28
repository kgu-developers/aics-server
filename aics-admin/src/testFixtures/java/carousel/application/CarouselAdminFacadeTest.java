package carousel.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.admin.carousel.application.CarouselAdminFacade;
import kgu.developers.admin.carousel.presentation.request.CarouselRequest;
import kgu.developers.admin.carousel.presentation.request.CarouselUpdateRequest;
import kgu.developers.admin.carousel.presentation.response.CarouselPersistResponse;
import kgu.developers.domain.carousel.application.command.CarouselCommandService;
import kgu.developers.domain.carousel.application.query.CarouselQueryService;
import kgu.developers.domain.carousel.domain.Carousel;
import kgu.developers.domain.carousel.exception.CarouselNotFoundException;
import kgu.developers.domain.file.application.query.FileQueryService;
import kgu.developers.domain.file.domain.FileModel;
import mock.repository.FakeCarouselRepository;
import mock.repository.FakeFileRepository;

public class CarouselAdminFacadeTest {
	private CarouselAdminFacade carouselAdminFacade;
	private Carousel carousel;

	private static final Long TEST_FILE_ID = 1L;

	@BeforeEach
	public void init() {
		initializeCarouselAdminFacade();
	}

	private void initializeCarouselAdminFacade() {
		FakeFileRepository fakeFileRepository = new FakeFileRepository();
		FakeCarouselRepository fakeCarouselRepository = new FakeCarouselRepository();
		carouselAdminFacade = new CarouselAdminFacade(
			new CarouselCommandService(
				new FileQueryService(fakeFileRepository),
				fakeCarouselRepository
			),
			new CarouselQueryService(fakeCarouselRepository)
		);
		saveTestFile(fakeFileRepository);

		carousel = fakeCarouselRepository.save(Carousel.create("text", "link", null));
	}

	private static void saveTestFile(FakeFileRepository fakeFileRepository) {
		fakeFileRepository.save(
			FileModel.create(
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
		assertEquals(2, response.id());
	}

	@Test
	@DisplayName("updateCarousel은 Carousel을 수정한다")
	public void updateCarousel_Success() {
		// given
		CarouselUpdateRequest request = new CarouselUpdateRequest("경기대학교 AI컴퓨터공학부 메인 이미지", "https://www.kgu.ac.kr/", TEST_FILE_ID);

		// when
		carouselAdminFacade.updateCarousel(carousel.getId(), request);

		// then
		assertEquals(request.text(), carousel.getText());
		assertEquals(request.link(), carousel.getLink());
		assertEquals(request.fileId(), carousel.getFileId());
	}

	@Test
	@DisplayName("deleteCarousel은 Carousel을 삭제한다.")
	public void deleteCarousel_Success() {
		// when
		carouselAdminFacade.deleteCarousel(carousel.getId());

		// then
		assertThatThrownBy(() -> {
			carouselAdminFacade.updateCarousel(carousel.getId(), null);
		}).isInstanceOf(CarouselNotFoundException.class);
	}
}

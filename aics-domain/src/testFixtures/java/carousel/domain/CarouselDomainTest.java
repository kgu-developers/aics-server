package carousel.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kgu.developers.domain.carousel.domain.Carousel;
import kgu.developers.domain.file.domain.FileEntity;

public class CarouselDomainTest {
	private static final String TARGET_CAROUSEL_TEXT = "컴퓨터공학부 대표 이미지";
	private static final String TARGET_CAROUSEL_LINK = "https://kgu.ac.kr";

	@Test
	@DisplayName("create 정적 팩토리 메서드는 Carousel 객체를 생성할 수 있다")
	public void createCarousel_success() {
		//given
		//when
		Carousel createdCarousel = Carousel.create(TARGET_CAROUSEL_TEXT, TARGET_CAROUSEL_LINK,
			FileEntity.builder().build());

		//then
		assertNotNull(createdCarousel);
		assertEquals(TARGET_CAROUSEL_TEXT, createdCarousel.getText());
		assertEquals(TARGET_CAROUSEL_LINK, createdCarousel.getLink());
	}

	@Test
	@DisplayName("updateText는 text를 수정한다.")
	public void updateText_success() {
		// given
		Carousel carousel = Carousel.builder().build();
		String text = "text";

		// when
		carousel.updateText(text);

		// then
		assertEquals(text, carousel.getText());
	}

	@Test
	@DisplayName("updateLink는 link를 수정한다.")
	public void updateLink_success() {
		// given
		Carousel carousel = Carousel.builder().build();
		String link = "link";

		// when
		carousel.updateLink(link);

		// then
		assertEquals(link, carousel.getLink());
	}

	@Test
	@DisplayName("updateFile은 file를 수정한다.")
	public void updateFile_success() {
		// given
		Carousel carousel = Carousel.builder().build();
		FileEntity file = FileEntity.builder().build();

		// when
		carousel.updateFile(file);

		// then
		assertEquals(file, carousel.getFile());
	}
}

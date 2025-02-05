package kgu.developers.api.about.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kgu.developers.api.about.application.AboutFacade;
import kgu.developers.api.about.presentation.response.AboutResponse;
import kgu.developers.domain.about.domain.Category;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/abouts")
public class AboutControllerImpl implements AboutController {
	private final AboutFacade aboutFacade;

	@Override
	@GetMapping
	public ResponseEntity<AboutResponse> getAbout(
		@RequestParam Category category
	) {
		AboutResponse response = aboutFacade.getAboutByCategory(category);
		return ResponseEntity.ok(response);
	}
}

package kgu.developers.admin.carousel.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.admin.carousel.application.CarouselAdminFacade;
import kgu.developers.admin.carousel.presentation.request.CarouselRequest;
import kgu.developers.admin.carousel.presentation.request.CarouselUpdateRequest;
import kgu.developers.admin.carousel.presentation.response.CarouselPersistResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carousels")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CarouselAdminControllerImpl implements CarouselAdminController {
	private final CarouselAdminFacade carouselAdminFacade;

	@Override
	@PostMapping
	public ResponseEntity<CarouselPersistResponse> createCarousel(
		@Positive @RequestParam Long fileId,
		@Valid @RequestBody CarouselRequest request
	) {
		CarouselPersistResponse response = carouselAdminFacade.createCarousel(fileId, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateCarousel(
		@Positive @PathVariable Long id,
		@Valid @RequestBody CarouselUpdateRequest request) {
		carouselAdminFacade.updateCarousel(id, request);
		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCarousel(
		@Positive @PathVariable Long id
	) {
		carouselAdminFacade.deleteCarousel(id);
		return ResponseEntity.noContent().build();
	}
}

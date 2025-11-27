package kgu.developers.api.thesis.presentation;

import kgu.developers.api.thesis.application.ThesisFacade;
import kgu.developers.api.thesis.presentation.request.ThesisSubmitRequest;
import kgu.developers.api.thesis.presentation.response.ThesisPersistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/thesis")
@RequiredArgsConstructor
public class ThesisControllerImpl implements ThesisController {
	private final ThesisFacade thesisFacade;

	@Override
	@PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ThesisPersistResponse> submitThesisAndSaveFile(
		@RequestPart(value = "file") MultipartFile file,
		@RequestPart ThesisSubmitRequest request
	) {
		Long id = thesisFacade.submitThesis(file, request.scheduleId());
		return ResponseEntity.ok(
			ThesisPersistResponse.of(id)
		);
	}
}

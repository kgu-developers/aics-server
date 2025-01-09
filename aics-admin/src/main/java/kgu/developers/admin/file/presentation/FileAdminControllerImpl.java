package kgu.developers.admin.file.presentation;

import static kgu.developers.domain.file.domain.FileDomain.ABOUT;
import static kgu.developers.domain.file.domain.FileDomain.CAROUSEL;
import static kgu.developers.domain.file.domain.FileDomain.LAB;
import static kgu.developers.domain.file.domain.FileDomain.POST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Positive;
import kgu.developers.admin.file.application.FileAdminFacade;
import kgu.developers.domain.file.application.response.FilePathResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class FileAdminControllerImpl implements FileAdminController {
	private final FileAdminFacade fileAdminFacade;

	@Override
	@PostMapping(value = "/post", consumes = MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FilePathResponse> postFileUpload(
		@RequestPart(value = "file") MultipartFile file,
		@Positive @RequestParam(required = false) Long id
	) {
		FilePathResponse path = fileAdminFacade.saveFile(file, POST, id);
		return ResponseEntity.status(CREATED).body(path);
	}

	@Override
	@PostMapping(value = "/about", consumes = MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FilePathResponse> aboutFileUpload(
		@RequestPart(value = "file") MultipartFile file,
		@Positive @RequestParam(required = false) Long id
	) {
		FilePathResponse path = fileAdminFacade.saveFile(file, ABOUT, id);
		return ResponseEntity.status(CREATED).body(path);
	}

	@Override
	@PostMapping(value = "/carousel", consumes = MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FilePathResponse> carouselFileUpload(
		@RequestPart(value = "file") MultipartFile file,
		@Positive @RequestParam(required = false) Long id
	) {
		FilePathResponse path = fileAdminFacade.saveFile(file, CAROUSEL, id);
		return ResponseEntity.status(CREATED).body(path);
	}

	@Override
	@PostMapping(value = "/lab", consumes = MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FilePathResponse> labFileUpload(
		@RequestPart(value = "file") MultipartFile file,
		@Positive @RequestParam(required = false) Long id
	) {
		FilePathResponse path = fileAdminFacade.saveFile(file, LAB, id);
		return ResponseEntity.status(CREATED).body(path);
	}
}

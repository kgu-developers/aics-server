package kgu.developers.api.certificate.presentation;

import kgu.developers.api.certificate.application.CertificateFacade;
import kgu.developers.api.certificate.presentation.response.CertificatePersistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/certificate")
@RequiredArgsConstructor
public class CertificateControllerImpl implements CertificateController {
	private final CertificateFacade certificateFacade;

	@Override
	@PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<CertificatePersistResponse> submitCertificateAndSaveFile(
		@RequestPart(value = "file") MultipartFile file
	) {
		Long id = certificateFacade.submitCertificate(file);
		return ResponseEntity.ok(
			CertificatePersistResponse.of(id)
		);
	}
}

package kgu.developers.api.certificate.presentation;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import kgu.developers.api.certificate.application.CertificateFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kgu.developers.api.certificate.presentation.request.CertificateSubmitRequest;
import kgu.developers.api.certificate.presentation.response.CertificatePersistResponse;
import kgu.developers.domain.certificate.application.command.CertificateCommandService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/certificate")
@RequiredArgsConstructor
public class CertificateControllerImpl implements CertificateController {
	private final CertificateFacade certificateFacade;

	@Override
	@PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<CertificatePersistResponse> submitCertificateAndSaveFile(
		@RequestPart(value = "file") MultipartFile file,
		@RequestPart CertificateSubmitRequest request
	) {
		Long id = certificateFacade.submitCertificate(file, request.scheduleId());
		return ResponseEntity.ok(
			CertificatePersistResponse.of(id)
		);
	}


}

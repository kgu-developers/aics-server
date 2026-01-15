package kgu.developers.api.certificate.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.api.certificate.presentation.response.CertificatePersistResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Tag(name = "Certificate", description = "자격증 관련 API")
public interface CertificateController {

	@Operation(
		summary = "자격증 파일 제출 API", description = """
		    - Description : 이 API는 자격증 파일을 제출합니다.
		    - Assignee : 이한음
		"""
	)
	@ApiResponse(
		responseCode = "200", content = @Content(schema = @Schema(implementation = CertificatePersistResponse.class))
	)
	ResponseEntity<CertificatePersistResponse> submitCertificateAndSaveFile(
		@Parameter(
			description = "자격증 첨부 파일",
			content = @Content(mediaType = MULTIPART_FORM_DATA_VALUE),
			required = true
		) @RequestPart(value = "file") MultipartFile file
	);
}

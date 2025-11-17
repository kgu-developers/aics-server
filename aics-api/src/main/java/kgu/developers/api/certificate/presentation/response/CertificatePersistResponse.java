package kgu.developers.api.certificate.presentation.response;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CertificatePersistResponse(
	@Schema(description = "자격증 객체 id", example = "1", requiredMode = REQUIRED)
	Long id
) {
	public static CertificatePersistResponse of(Long id) {
		return CertificatePersistResponse.builder().id(id).build();
	}
}

package kgu.developers.admin.certificate.presentation;

import kgu.developers.admin.certificate.application.CertificateAdminFacade;
import kgu.developers.admin.certificate.presentation.response.CertificateDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/certificate")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CertificateAdminControllerImpl implements CertificateAdminController {
    private final CertificateAdminFacade certificateAdminFacade;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CertificateDetailResponse> getCertificate(@PathVariable Long id) {
        return ResponseEntity.ok(certificateAdminFacade.getById(id));
    }
}

package kgu.developers.admin.thesis.presentation;

import kgu.developers.admin.thesis.application.ThesisAdminFacade;
import kgu.developers.admin.thesis.presentation.response.ThesisDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/thesis")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ThesisAdminControllerImpl implements ThesisAdminController {
    private final ThesisAdminFacade thesisAdminFacade;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ThesisDetailResponse> getThesis(@PathVariable Long id) {
        return ResponseEntity.ok(thesisAdminFacade.getById(id));
    }
}

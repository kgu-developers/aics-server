package kgu.developers.admin.graduationUser.presentation;

import jakarta.validation.Valid;
import kgu.developers.admin.graduationUser.application.GraduationUserAdminFacade;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserCreateRequest;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserPersistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/graduation-user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class GraduationUserAdminControllerImpl implements GraduationUserAdminController {
    private final GraduationUserAdminFacade graduationUserAdminFacade;

    @Override
    @PostMapping
    public ResponseEntity<GraduationUserPersistResponse> createGraduationUser(
        @Valid @RequestBody GraduationUserCreateRequest request
    ) {
        GraduationUserPersistResponse response = graduationUserAdminFacade.createGraduationUser(request);
        return ResponseEntity.status(CREATED).body(response);
    }
}

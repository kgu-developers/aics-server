package kgu.developers.admin.graduationUser.presentation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import kgu.developers.admin.graduationUser.application.GraduationUserAdminFacade;
import kgu.developers.admin.graduationUser.presentation.request.GraduationUserCreateRequest;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserPersistResponse;
import kgu.developers.admin.graduationUser.presentation.response.GraduationUserSummaryPageResponse;
import kgu.developers.domain.graduationUser.domain.GraduationType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Override
    @GetMapping
    public ResponseEntity<GraduationUserSummaryPageResponse> getGraduationUsersByName(
        @PositiveOrZero @RequestParam(defaultValue = "0") int page,
        @Positive @RequestParam(defaultValue = "10") int size,
        @RequestParam (required = false) String name,
        @RequestParam (required = false) GraduationType graduationType
        ) {
        GraduationUserSummaryPageResponse response = graduationUserAdminFacade.getUsersByNameAndGraduationType(PageRequest.of(page,size), name,
            graduationType);
        return ResponseEntity.ok(response);
    }
}

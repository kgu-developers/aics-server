package kgu.developers.api.graduationUser.presentation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.api.graduationUser.application.GraduationUserFacade;
import kgu.developers.api.graduationUser.presentation.request.GraduationTypeUpdateRequest;
import kgu.developers.api.graduationUser.presentation.request.GraduationUserEmailUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/api/v1/graduation-users")
public class GraduationUserControllerImpl implements GraduationUserController {

    private final GraduationUserFacade graduationuserFacade;

    @Override
    @PatchMapping("/{graduationUserId}/graduation-type")
    public ResponseEntity<Void> selectGraduationType(
        @Positive @PathVariable Long graduationUserId,
        @Valid @RequestBody GraduationTypeUpdateRequest request) {
        graduationuserFacade.updateGraduationType(graduationUserId,request.graduationType());
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{graduationUserId}/email")
    public ResponseEntity<Void> updateGraduationUserEmail(
        @Positive @PathVariable Long graduationUserId,
        @Valid @RequestBody GraduationUserEmailUpdateRequest request) {
        graduationuserFacade.updateGraduationUserEmail(graduationUserId,request.email());
        return ResponseEntity.noContent().build();
    }
}

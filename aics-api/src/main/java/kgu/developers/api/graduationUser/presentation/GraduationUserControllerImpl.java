package kgu.developers.api.graduationUser.presentation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.api.graduationUser.application.GraduationuserFacade;
import kgu.developers.api.graduationUser.presentation.request.GraduationTypeUpdateRequest;
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
@RequestMapping("/api/v1/graduation-users")
public class GraduationUserControllerImpl implements GraduationUserController {

    private final GraduationuserFacade graduationuserFacade;

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @PatchMapping("/{graduationUserId}")
    public ResponseEntity<Void> selectGraduationType(
        @Positive @PathVariable Long graduationUserId,
        @Valid @RequestBody GraduationTypeUpdateRequest request) {
        graduationuserFacade.selectGraduationType(graduationUserId,request.graduationType());
        return ResponseEntity.noContent().build();
    }
}

package kgu.developers.api.graduationUser.presentation;

import jakarta.validation.Valid;
import kgu.developers.api.graduationUser.application.GraduationUserFacade;
import kgu.developers.api.graduationUser.presentation.request.GraduationTypeUpdateRequest;
import kgu.developers.api.graduationUser.presentation.request.GraduationUserEmailUpdateRequest;
import kgu.developers.api.graduationUser.presentation.response.MyGraduationUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    @PatchMapping("/graduation-type")
    public ResponseEntity<Void> selectGraduationType(
        @Valid @RequestBody GraduationTypeUpdateRequest request) {
        graduationuserFacade.updateGraduationType(request.graduationType());
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/email")
    public ResponseEntity<Void> updateGraduationUserEmail(
        @Valid @RequestBody GraduationUserEmailUpdateRequest request) {
        graduationuserFacade.updateGraduationUserEmail(request.email());
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/my")
    public ResponseEntity<MyGraduationUserResponse> getMyGraduationUser() {
        MyGraduationUserResponse response = graduationuserFacade.getMyGraduationUser();
        return ResponseEntity.ok(response);
    }
}

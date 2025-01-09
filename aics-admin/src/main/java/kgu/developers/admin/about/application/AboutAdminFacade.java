package kgu.developers.admin.about.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.admin.about.presentation.request.AboutCreateRequest;
import kgu.developers.admin.about.presentation.request.AboutUpdateRequest;
import kgu.developers.admin.about.presentation.response.AboutPersistResponse;
import kgu.developers.domain.about.application.command.AboutCommandService;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class AboutAdminFacade {
	private final AboutCommandService aboutCommandService;

	public AboutPersistResponse createAbout(AboutCreateRequest request) {
		Long id = aboutCommandService.createAbout(request.main(), request.sub(), request.detail(), request.content());
		return AboutPersistResponse.of(id);
	}

	public void updateAbout(Long id, AboutUpdateRequest request) {
		aboutCommandService.updateAbout(id, request.content());
	}
}

package kgu.developers.admin.club.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.admin.club.presentation.request.ClubRequest;
import kgu.developers.admin.club.presentation.response.ClubPersistResponse;
import kgu.developers.domain.club.application.command.ClubCommandService;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class ClubAdminFacade {
	private final ClubCommandService clubCommandService;

	public ClubPersistResponse createClub(ClubRequest request) {
		Long id = clubCommandService.createClub(request.name(), request.description(), request.site());
		return ClubPersistResponse.of(id);
	}
}

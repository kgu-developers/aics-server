package kgu.developers.admin.club.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.admin.club.presentation.request.ClubRequest;
import kgu.developers.admin.club.presentation.response.ClubPersistResponse;
import kgu.developers.domain.club.application.command.ClubCommandService;
import kgu.developers.domain.club.application.query.ClubQueryService;
import kgu.developers.domain.club.domain.Club;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class ClubAdminFacade {
	private final ClubCommandService clubCommandService;
	private final ClubQueryService clubQueryService;

	public ClubPersistResponse createClub(Long fileId, ClubRequest request) {
		Long id = clubCommandService.createClub(request.name(), request.description(), request.site(), fileId);
		return ClubPersistResponse.of(id);
	}

	public void updateClub(Long id, ClubRequest request) {
		Club club = clubQueryService.getById(id);
		clubCommandService.updateClub(club, request.name(), request.description(), request.site());
	}

	public void deleteClub(Long id) {
		clubCommandService.deleteClubById(id);
	}
}

package kgu.developers.api.club.application;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.api.club.presentation.response.ClubListResponse;
import kgu.developers.domain.club.application.query.ClubQueryService;
import kgu.developers.domain.club.domain.Club;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubFacade {
	private final ClubQueryService clubQueryService;

	public ClubListResponse getClubs() {
		List<Club> clubs = clubQueryService.getClubs();
		return ClubListResponse.from(clubs);
	}
}

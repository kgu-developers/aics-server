package kgu.developers.api.club.application;

import java.util.List;
import java.util.Objects;

import kgu.developers.domain.file.application.query.FileQueryService;
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
	private final FileQueryService fileQueryService;

	public ClubListResponse getClubs() {
		List<Club> clubs = clubQueryService.getClubs();

		List<Long> fileIds = clubs.stream()
				.map(Club::getFileId)
				.filter(Objects::nonNull)
				.toList();

		return ClubListResponse.from(clubs,fileQueryService.findFileEntityMapByIds(fileIds));
	}
}

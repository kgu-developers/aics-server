package kgu.developers.api.club.application;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import kgu.developers.domain.file.application.query.FileQueryService;
import kgu.developers.domain.file.domain.FileEntity;
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

		Map<Long, FileEntity> fileMap =
				fileQueryService.findAllByIds(fileIds)
						.stream()
						.collect(Collectors.toMap(FileEntity::getId,
				Function.identity()));

		return ClubListResponse.from(clubs,fileMap);
	}
}

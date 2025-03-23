package kgu.developers.domain.club.application.command;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.domain.club.domain.Club;
import kgu.developers.domain.club.domain.ClubRepository;
import kgu.developers.domain.file.application.query.FileQueryService;
import kgu.developers.domain.file.domain.FileEntity;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubCommandService {
	private final ClubRepository clubRepository;
	private final FileQueryService fileQueryService;

	public Long createClub(String name, String description, String site, Long fileId) {
		FileEntity file = null;
		if (fileId != null)
			file = fileQueryService.getFileById(fileId);

		Club club = Club.create(name, description, site, file);

		return clubRepository.save(club).getId();
	}

	public void updateClub(Club club, String name, String description, String site, Long fileId) {
		club.updateName(name);
		club.updateDescription(description);
		club.updateSite(site);

		FileEntity file = null;
		if (fileId != null) {
			file = fileQueryService.getFileById(fileId);
		}
		club.updateFile(file);
	}

	public void deleteClubById(Long id) {
		clubRepository.deleteById(id);
	}
}

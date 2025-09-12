package kgu.developers.domain.club.application.command;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.domain.club.domain.Club;
import kgu.developers.domain.club.domain.ClubRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubCommandService {
	private final ClubRepository clubRepository;

	public Long createClub(String name, String description, String site, Long fileId) {

		Club club = Club.create(name, description, site, fileId);

		return clubRepository.save(club).getId();
	}

	public void updateClub(Club club, String name, String description, String site, Long fileId) {
		club.updateName(name);
		club.updateDescription(description);
		club.updateSite(site);
		club.updateFileId(fileId);
		clubRepository.save(club);
	}

	public void deleteClubById(Long id) {
		clubRepository.deleteById(id);
	}
}

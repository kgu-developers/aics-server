package kgu.developers.domain.club.application.command;

import org.springframework.stereotype.Service;

import kgu.developers.domain.club.domain.Club;
import kgu.developers.domain.club.domain.ClubRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubCommandService {
	private final ClubRepository clubRepository;

	public Long createClub(String name, String description, String site) {
		Club club = Club.create(name, description, site);
		return clubRepository.save(club).getId();
	}

	public void updateClub(Club club, String name, String description, String site) {
		club.updateName(name);
		club.updateDescription(description);
		club.updateSite(site);
	}

	public void deleteClubById(Long id) {
		clubRepository.deleteById(id);
	}
}

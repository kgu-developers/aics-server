package kgu.developers.domain.club.application.query;

import java.util.List;

import org.springframework.stereotype.Service;

import kgu.developers.domain.club.domain.Club;
import kgu.developers.domain.club.domain.ClubRepository;
import kgu.developers.domain.club.exception.ClubNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubQueryService {
	private final ClubRepository clubRepository;

	public List<Club> getClubs() {
		return clubRepository.findAll();
	}

	public Club getById(Long id) {
		return clubRepository.findById(id).orElseThrow(ClubNotFoundException::new);
	}
}

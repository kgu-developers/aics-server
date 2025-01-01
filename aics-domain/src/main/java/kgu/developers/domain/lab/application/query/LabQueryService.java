package kgu.developers.domain.lab.application.query;

import java.util.List;

import org.springframework.stereotype.Service;

import kgu.developers.domain.lab.domain.Lab;
import kgu.developers.domain.lab.domain.LabRepository;
import kgu.developers.domain.lab.exception.LabNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabQueryService {
	private final LabRepository labRepository;

	public List<Lab> getLabsByName() {
		return labRepository.findAllByOrderByName();
	}

	public Lab getById(Long id) {
		return labRepository.findById(id).orElseThrow(LabNotFoundException::new);
	}
}

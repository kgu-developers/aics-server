package kgu.developers.domain.about.application.command;

import org.springframework.stereotype.Service;

import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.AboutRepository;
import kgu.developers.domain.about.domain.Category;
import kgu.developers.domain.about.exception.AboutNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AboutCommandService {
	private final AboutRepository aboutRepository;

	public Long createAbout(Category category, String description, String content) {
		About about = About.create(category, description, content);
		return aboutRepository.save(about).getId();
	}

	public void updateAbout(Long id, String description, String content) {
		About about = aboutRepository.findById(id)
			.orElseThrow(AboutNotFoundException::new);
		about.updateDescription(description);
		about.updateContent(content);
	}
}

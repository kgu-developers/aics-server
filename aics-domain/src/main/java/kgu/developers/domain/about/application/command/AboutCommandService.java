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

	public Long createAbout(Category category, String content) {
		About about = About.create(category, content);
		return aboutRepository.save(about).getId();
	}

	public void updateAbout(Long id, String content) {
		About about = aboutRepository.findById(id)
			.orElseThrow(AboutNotFoundException::new);
		about.updateContent(content);
	}
}

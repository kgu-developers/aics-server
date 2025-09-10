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
		About savedAbout = aboutRepository.save(about);
		return savedAbout.getId();
	}

	public void updateAbout(Category category, String content) {
		About about = aboutRepository.findByCategory(category)
			.orElseThrow(AboutNotFoundException::new);
		about.updateContent(content);
		aboutRepository.save(about);
	}
}

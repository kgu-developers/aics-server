package kgu.developers.domain.about.application.command;

import static kgu.developers.domain.about.domain.About.categoryMatchCheck;

import org.springframework.stereotype.Service;

import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.AboutRepository;
import kgu.developers.domain.about.domain.MainCategory;
import kgu.developers.domain.about.domain.SubCategory;
import kgu.developers.domain.about.exception.AboutNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AboutCommandService {
	private final AboutRepository aboutRepository;

	public Long createAbout(MainCategory main, SubCategory sub, String detail, String content) {
		categoryMatchCheck(main, sub);
		About about = About.create(main, sub, detail, content);
		return aboutRepository.save(about).getId();
	}

	public void updateAbout(Long id, String content) {
		About about = aboutRepository.findById(id)
			.orElseThrow(AboutNotFoundException::new);
		about.updateContent(content);
	}
}

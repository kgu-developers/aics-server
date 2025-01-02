package kgu.developers.api.about.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.api.about.presentation.response.AboutResponse;
import kgu.developers.domain.about.application.query.AboutQueryService;
import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.MainCategory;
import kgu.developers.domain.about.domain.SubCategory;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AboutFacade {
	private final AboutQueryService aboutQueryService;

	public AboutResponse getAbout(MainCategory main, SubCategory sub, String detail) {
		About about = aboutQueryService.getAbout(main, sub, detail);
		return AboutResponse.from(about);
	}
}

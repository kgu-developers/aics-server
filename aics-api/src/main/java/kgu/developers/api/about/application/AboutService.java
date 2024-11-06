package kgu.developers.api.about.application;

import kgu.developers.api.about.presentation.request.AboutRequest;
import kgu.developers.api.about.presentation.response.AboutPersistResponse;
import kgu.developers.api.about.presentation.response.AboutResponse;
import kgu.developers.domain.about.domain.AboutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AboutService {
	private final AboutRepository aboutRepository;

	public AboutPersistResponse createAbout(AboutRequest request) {
		return null;
	}

	public AboutResponse getAbout(String main, String sub, String detail) {
		return null;
	}

	public void updateAbout(Long id, AboutRequest request) {

	}
}

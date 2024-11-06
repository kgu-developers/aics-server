package kgu.developers.api.about.application;

import kgu.developers.domain.about.domain.AboutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AboutService {
	private final AboutRepository aboutRepository;
}

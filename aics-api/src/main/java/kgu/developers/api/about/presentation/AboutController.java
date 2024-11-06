package kgu.developers.api.about.presentation;

import kgu.developers.api.about.application.AboutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/abouts")
public class AboutController {
	private final AboutService aboutService;
}

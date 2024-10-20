package kgu.developers.apis.api.major.presentation;

import kgu.developers.apis.api.major.application.MajorService;
import kgu.developers.apis.api.major.presentation.request.MajorCreateRequest;
import kgu.developers.apis.api.major.presentation.response.MajorPersistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/major")
public class MajorController {
    private final MajorService majorService;

    @PostMapping("/create")
    public MajorPersistResponse createMajor(@RequestBody MajorCreateRequest request) {
        return majorService.createMajor(request);
    }

}

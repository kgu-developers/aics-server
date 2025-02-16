package kgu.developers.api.post.presentation;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import kgu.developers.api.post.application.PostFacade;
import kgu.developers.api.post.presentation.response.PostSummaryPageResponse;
import kgu.developers.domain.post.application.response.PostDetailResponse;
import kgu.developers.domain.post.domain.Category;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostControllerImpl implements PostController {
	private final PostFacade postFacade;

	@Override
	@GetMapping
	public ResponseEntity<PostSummaryPageResponse> getPostsByKeywordAndCategory(
		@PositiveOrZero @RequestParam(defaultValue = "0") int page,
		@Positive @RequestParam(defaultValue = "10")  int size,
		@RequestParam(required = false) List<String> keywords,
		@RequestParam(required = false) Category category
	) {
		PostSummaryPageResponse response = postFacade.getPostsByKeywordAndCategory(PageRequest.of(page, size), keywords,
			category);
		return ResponseEntity.ok(response);
	}

	@Override
	@GetMapping("/{postId}")
	public ResponseEntity<PostDetailResponse> getPostById(
		@PathVariable @Positive Long postId
	) {
		PostDetailResponse response = postFacade.getPostByIdWithPrevAndNext(postId);
		return ResponseEntity.ok(response);
	}
}

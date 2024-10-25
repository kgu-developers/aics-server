package kgu.developers.apis.api.post.presentation;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.apis.api.post.application.PostService;
import kgu.developers.apis.api.post.presentation.request.PostCreateRequest;
import kgu.developers.apis.api.post.presentation.response.PostInfoResponse;
import kgu.developers.apis.api.post.presentation.response.PostPageResponse;
import kgu.developers.apis.api.post.presentation.response.PostPersistResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Tag(name = "Post", description = "게시글 API")
public class PostController {
	private final PostService postService;

	@Operation(summary = "게시글 API", description = """
			- Description : 이 API는 게시글 생성을 요청합니다.
			- Assignee : 박민준
		""")
	@PostMapping
	public ResponseEntity<PostPersistResponse> createPost(@RequestBody PostCreateRequest request) {
		PostPersistResponse response = postService.createPost(request);

		return ResponseEntity.status(CREATED).body(response);
	}

	@Operation(summary = "게시글 조회 API", description = """
		    - Description : 게시글을 페이지 단위로 조회하고, 키워드 검색을 지원합니다.
		    - Assignee : 박민준
		""")
	@ApiResponse(
		responseCode = "200",
		description = "게시글 조회 성공",
		content = @Content(schema = @Schema(implementation = PostPageResponse.class))
	)
	@GetMapping
	public ResponseEntity<PostPageResponse<PostInfoResponse>> getPosts(
		@RequestParam(required = false) String keyword,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	) {
		PostPageResponse<PostInfoResponse> response = postService.getPosts(keyword, page, size);

		return ResponseEntity.ok(response);
	}
}

package kgu.developers.apis.api.post.presentation;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.apis.api.post.application.PostService;
import kgu.developers.apis.api.post.presentation.request.PostCreateRequest;
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
}

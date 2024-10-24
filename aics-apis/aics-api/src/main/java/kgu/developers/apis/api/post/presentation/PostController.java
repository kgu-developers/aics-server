package kgu.developers.apis.api.post.presentation;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kgu.developers.apis.api.post.application.PostService;
import kgu.developers.apis.api.post.presentation.request.PostCreateRequest;
import kgu.developers.apis.api.post.presentation.response.PostCreateResponse;
import kgu.developers.apis.api.user.application.UserService;
import kgu.developers.core.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
	private final PostService postService;
	private final UserService userService;

	@PostMapping
	public ResponseEntity<PostCreateResponse> createPost(@RequestBody PostCreateRequest request) {
		User author = userService.me();
		PostCreateResponse response = postService.createPost(request, author);

		return ResponseEntity.status(CREATED).body(response);
	}
}

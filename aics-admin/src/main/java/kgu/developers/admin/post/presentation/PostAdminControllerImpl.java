package kgu.developers.admin.post.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kgu.developers.admin.post.application.PostAdminFacade;
import kgu.developers.admin.post.presentation.request.PostCreateRequest;
import kgu.developers.admin.post.presentation.request.PostUpdateRequest;
import kgu.developers.admin.post.presentation.response.PostPersistResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/posts")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class PostAdminControllerImpl implements PostAdminController {
	private final PostAdminFacade postAdminFacade;

	@Override
	@PostMapping
	public ResponseEntity<PostPersistResponse> createPost(
		@RequestParam(required = false) Long fileId,
		@Valid @RequestBody PostCreateRequest request
	) {
		PostPersistResponse response = postAdminFacade.createPost(fileId, request);
		return ResponseEntity.status(CREATED).body(response);
	}

	@Override
	@PatchMapping("/{postId}")
	public ResponseEntity<Void> updatePost(
		@Positive @PathVariable Long postId,
		@Valid @RequestBody PostUpdateRequest request
	) {
		postAdminFacade.updatePost(postId, request);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PatchMapping("/{postId}/pin")
	public ResponseEntity<Void> togglePostPinStatus(
		@Positive @PathVariable Long postId
	) {
		postAdminFacade.togglePostPinStatus(postId);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PatchMapping("/{postId}/delete")
	public ResponseEntity<Void> deletePostById(
		@Positive @PathVariable Long postId
	) {
		postAdminFacade.deletePost(postId);
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping("/cleanup-last-run")
	public ResponseEntity<String> getLastCleanupRunTime() {
		String response = postAdminFacade.getLastCleanupRunTime();
		return ResponseEntity.ok(response);
	}
}

package kgu.developers.api.comment.presentation;

import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.api.comment.application.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1//comments")
@Tag(name = "Post", description = "댓글 API")
public class CommentController {
	private final CommentService commentService;
}

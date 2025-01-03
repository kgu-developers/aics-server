package kgu.developers.admin.comment.presentation;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import kgu.developers.admin.comment.application.CommentAdminFacade;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Tag(name = "Comment", description = "댓글 관리자 API")
public class CommentAdminController {
	private final CommentAdminFacade commentAdminFacade;

	// TODO : 관리자 권한 댓글 삭제 API 구현
}

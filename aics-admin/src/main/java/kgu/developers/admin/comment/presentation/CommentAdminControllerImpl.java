package kgu.developers.admin.comment.presentation;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kgu.developers.admin.comment.application.CommentAdminFacade;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/comments")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CommentAdminControllerImpl implements CommentAdminController {
	private final CommentAdminFacade commentAdminFacade;
}

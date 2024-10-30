package kgu.developers.api.comment.application;

import kgu.developers.api.comment.presentation.request.CommentRequest;
import kgu.developers.api.comment.presentation.response.CommentResponse;
import kgu.developers.domain.comment.domain.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;

	// TODO 서비스 레이어 구현
	public CommentResponse createComment(Long postId, CommentRequest commentRequest) {
		return null;
	}
}

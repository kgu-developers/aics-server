package kgu.developers.api.post.application;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgu.developers.api.post.presentation.response.PostSummaryPageResponse;
import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.post.application.command.PostCommandService;
import kgu.developers.domain.post.application.query.PostQueryService;
import kgu.developers.domain.post.application.response.PostDetailResponse;
import kgu.developers.domain.post.domain.Category;
import kgu.developers.domain.post.domain.Post;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostFacade {
	private final PostCommandService postCommandService;
	private final PostQueryService postQueryService;

	public PostSummaryPageResponse getPostsByKeywordAndCategory(PageRequest request, String keyword,
		Category category) {
		PaginatedListResponse<Post> paginatedListResponse = postQueryService.getPostsByKeywordAndCategory(request,
			keyword, category);
		return PostSummaryPageResponse.of(paginatedListResponse.contents(), paginatedListResponse.pageable());
	}

	@Transactional
	public PostDetailResponse getPostByIdWithPrevAndNext(Long postId) {
		Post post = postQueryService.getById(postId);
		postCommandService.increaseViews(post);
		return postQueryService.getPostByIdWithPrevAndNext(post);
	}
}

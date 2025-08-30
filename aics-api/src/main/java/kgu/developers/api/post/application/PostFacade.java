package kgu.developers.api.post.application;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
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
	private final UserQueryService userQueryService;

	public PostSummaryPageResponse getPostsByKeywordAndCategory(PageRequest request, List<String> keywords,
		Category category) {
		PaginatedListResponse<Post> paginatedListResponse = postQueryService.getPostsByKeywordAndCategory(request,
			keywords, category);

		List<String> authorIds = paginatedListResponse.contents().stream()
			.map(Post::getAuthorId)
			.distinct()
			.toList();

		Map<String, String> authorNameMap = userQueryService.getAllUsersByIds(authorIds).stream()
			.collect(Collectors.toMap(
				User::getId,
				User::getName
			));

		return PostSummaryPageResponse.of(paginatedListResponse.contents(),
			paginatedListResponse.pageable(), authorNameMap);
	}

	@Transactional
	public PostDetailResponse getPostByIdWithPrevAndNext(Long postId) {
		Post post = postQueryService.getById(postId);
		postCommandService.increaseViews(post);
		return postQueryService.getPostByIdWithPrevAndNext(post);
	}
}

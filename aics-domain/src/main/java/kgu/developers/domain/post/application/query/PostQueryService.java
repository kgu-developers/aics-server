package kgu.developers.domain.post.application.query;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kgu.developers.common.response.PaginatedListResponse;
import kgu.developers.domain.post.application.response.PostDetailResponse;
import kgu.developers.domain.post.application.response.PostTitleResponse;
import kgu.developers.domain.post.domain.Category;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.post.domain.PostRepository;
import kgu.developers.domain.post.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostQueryService {
	private final PostRepository postRepository;

	public PaginatedListResponse<Post> getPostsByKeywordAndCategory(PageRequest request, List<String> keywords,
		Category category) {
		return postRepository.findAllByTitleContainingAndCategoryOrderByCreatedAtDesc(
			keywords, category, request);
	}

	public PostDetailResponse getPostByIdWithPrevAndNext(Post post) {
		LocalDateTime timestamp = post.getCreatedAt();
		Category category = post.getCategory();

		Post prevPost = postRepository.findByPrevPost(timestamp, category).orElse(null);
		Post nextPost = postRepository.findByNextPost(timestamp, category).orElse(null);

		PostTitleResponse prevPostResponse = PostTitleResponse.from(prevPost);
		PostTitleResponse nextPostResponse = PostTitleResponse.from(nextPost);
		return PostDetailResponse.from(post, prevPostResponse, nextPostResponse);
	}

	public Post getById(Long postId) {
		return postRepository.findByIdAndDeletedAtIsNull(postId)
			.orElseThrow(PostNotFoundException::new);
	}
}

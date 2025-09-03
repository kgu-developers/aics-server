package kgu.developers.domain.post.application.query;

import java.time.LocalDateTime;
import java.util.List;

import kgu.developers.domain.file.domain.FileEntity;
import kgu.developers.domain.file.domain.FileRepository;
import kgu.developers.domain.user.domain.User;
import kgu.developers.domain.user.domain.UserRepository;
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
	private final FileRepository fileRepository;
	private final UserRepository userRepository;

	public PaginatedListResponse<Post> getPostsByKeywordAndCategory(PageRequest request, List<String> keywords,
		Category category) {
		return postRepository.findAllByTitleContainingAndCategoryOrderByCreatedAtDescIdDesc(
			keywords, category, request);
	}

	public PostDetailResponse getPostByIdWithPrevAndNext(Post post) {

		Long postId = post.getId();
		LocalDateTime timestamp = post.getCreatedAt();
		Category category = post.getCategory();

		Post prevPost = postRepository.findByPrevPost(postId, timestamp, category).orElse(null);
		Post nextPost = postRepository.findByNextPost(postId, timestamp, category).orElse(null);

		FileEntity file = fileRepository.findById(post.getFileId()).orElse(null);
		User author = userRepository.findById(post.getAuthorId()).orElse(null);
		PostTitleResponse prevPostResponse = PostTitleResponse.from(prevPost);
		PostTitleResponse nextPostResponse = PostTitleResponse.from(nextPost);
		return PostDetailResponse.from(post, author, file, prevPostResponse, nextPostResponse);
	}

	public Post getById(Long postId) {
		return postRepository.findByIdAndDeletedAtIsNull(postId)
			.orElseThrow(PostNotFoundException::new);
	}
}

package kgu.developers.apis.api.post.application;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kgu.developers.apis.api.post.presentation.request.PostCreateRequest;
import kgu.developers.apis.api.post.presentation.response.PostCreateResponse;
import kgu.developers.core.domain.post.Post;
import kgu.developers.core.domain.post.PostRepository;
import kgu.developers.core.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;

	@Transactional
	public PostCreateResponse createPost(PostCreateRequest request, User author) {
		Post savePost = postRepository.save(Post.create(
			request.title(), request.content(), request.category(), author
		));

		return PostCreateResponse.from(savePost);
	}
}

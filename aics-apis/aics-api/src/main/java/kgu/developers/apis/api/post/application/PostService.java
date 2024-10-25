package kgu.developers.apis.api.post.application;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kgu.developers.apis.api.post.presentation.request.PostCreateRequest;
import kgu.developers.apis.api.post.presentation.response.PostPersistResponse;
import kgu.developers.apis.api.user.application.UserService;
import kgu.developers.core.domain.post.Post;
import kgu.developers.core.domain.post.PostRepository;
import kgu.developers.core.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;
	private final UserService userService;

	@Transactional
	public PostPersistResponse createPost(PostCreateRequest request) {
		User author = userService.me();
		Post createPost = Post.create(
			request.title(), request.content()
		);

		author.addPost(createPost);
		postRepository.save(createPost);

		return PostPersistResponse.from(createPost.getId());
	}
}

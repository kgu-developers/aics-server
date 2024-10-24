package kgu.developers.apis.api.post.application;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kgu.developers.apis.api.post.presentation.request.PostCreateRequest;
import kgu.developers.apis.api.post.presentation.response.PostCreateResponse;
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
	public PostCreateResponse createPost(PostCreateRequest request) {
		// fetch join을 사용할까요 ...? 아님 이 방법이 좋을까요?
		// user 서비스에서 post 서비스를 사용할 일은 없을 것 같고
		// 페치조인을 사용하면 다시 사용할 일이 있을까? 해서 이렇게 구현했슴다
		User author = userService.me();
		Post savePost = Post.create(
			request.title(), request.content(), request.category(), author
		);

		postRepository.save(savePost);

		return PostCreateResponse.from(savePost);
	}
}

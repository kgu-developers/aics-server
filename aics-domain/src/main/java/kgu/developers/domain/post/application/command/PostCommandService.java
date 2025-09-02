package kgu.developers.domain.post.application.command;

import org.springframework.stereotype.Service;

import kgu.developers.domain.file.application.query.FileQueryService;
import kgu.developers.domain.post.domain.Category;
import kgu.developers.domain.post.domain.Post;
import kgu.developers.domain.post.domain.PostRepository;
import kgu.developers.domain.user.application.query.UserQueryService;
import kgu.developers.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostCommandService {
	private final UserQueryService userQueryService;
	private final PostRepository postRepository;
	private final FileQueryService fileQueryService;

	public Long createPost(String title, String content, Category category, Long fileId, boolean isPinned) {
		User author = userQueryService.me();

		Post post = Post.create(title, content, category, author.getId(), fileId, isPinned);
		return postRepository.save(post).getId();
	}

	public void updatePost(Post post, String title, String content, Category category, Long fileId, boolean isPinned) {
		post.updateTitle(title);
		post.updateContent(content);
		post.updateCategory(category);
		post.updatePinned(isPinned);
		post.updateFileId(fileId);
	}

	public void togglePostPinStatus(Post post) {
		post.togglePinned();
	}

	public void increaseViews(Post post) {
		post.increaseViews();
	}

	public void deletePost(Post post) {
		post.delete();
	}
}

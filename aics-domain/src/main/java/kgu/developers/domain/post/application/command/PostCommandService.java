package kgu.developers.domain.post.application.command;

import org.springframework.stereotype.Service;

import kgu.developers.domain.file.application.query.FileQueryService;
import kgu.developers.domain.file.domain.FileEntity;
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

	public Long createPost(String title, String content, Category category, Long fileId) {
		User author = userQueryService.me();

		FileEntity file = null;
		if (fileId != null)
			file = fileQueryService.getFileById(fileId);

		Post post = Post.create(title, content, category, author, file);
		return postRepository.save(post).getId();
	}

	public void updatePost(Post post, String title, String content, Category category, Long fileId) {
		post.updateTitle(title);
		post.updateContent(content);
		post.updateCategory(category);

		FileEntity file = null;
		if (fileId != null)
			file = fileQueryService.getFileById(fileId);

		post.updateFile(file);
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

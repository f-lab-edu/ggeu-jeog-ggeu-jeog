package com.rollingpaper.ggeujeogggeujeog.comment.domain;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

	void save(Comment comment);

	Optional<Comment> findByPaperId(Long paperId);

	List<Comment> findAllByUserId(Long userId, int page);

	void delete(Long paperId);

	void update(Comment comment);
}

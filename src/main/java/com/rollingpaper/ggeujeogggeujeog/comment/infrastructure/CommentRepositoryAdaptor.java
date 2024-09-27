package com.rollingpaper.ggeujeogggeujeog.comment.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.rollingpaper.ggeujeogggeujeog.comment.domain.Comment;
import com.rollingpaper.ggeujeogggeujeog.comment.domain.CommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryAdaptor implements CommentRepository {

	private final CommentMapper commentMapper;

	@Override
	public void save(Comment comment) {
		commentMapper.save(comment);
	}

	@Override
	public Optional<Comment> findByPaperId(Long paperId) {
		return commentMapper.findByPaperId(paperId);
	}

	@Override
	public List<Comment> findAllByUserId(Long userId, int page) {
		return commentMapper.findAllByUserId(userId, page);
	}

	@Override
	public void delete(Long paperId) {
		commentMapper.delete(paperId);
	}

	@Override
	public void update(Comment comment) {
		commentMapper.update(comment);
	}
}

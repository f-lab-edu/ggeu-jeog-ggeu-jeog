package com.rollingpaper.ggeujeogggeujeog.board.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Board;
import com.rollingpaper.ggeujeogggeujeog.board.domain.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardRepositoryAdaptor implements BoardRepository {

	private final BoardMapper boardMapper;

	@Override
	public void save(Board board) {
		boardMapper.save(board);
	}

	@Override
	public List<Board> findByUserId(Long userId) {
		return boardMapper.findByUserId(userId);
	}

	@Override
	public void delete(Long boardId) {
		boardMapper.delete(boardId);
	}

	@Override
	public void update(Board board) {
		boardMapper.update(board);
	}

	@Override
	public Optional<Board> findById(Long boardId) {
		return boardMapper.findById(boardId);
	}

	@Override
	public List<Board> findAllBoards(boolean isOpened) {
		return boardMapper.findAllBoards(isOpened);
	}

	@Override
	public List<Board> findAllTaggedBoards(List<String> tagNames, boolean isOpened) {
		return boardMapper.findAllTaggedBoards(tagNames, isOpened);
	}
}

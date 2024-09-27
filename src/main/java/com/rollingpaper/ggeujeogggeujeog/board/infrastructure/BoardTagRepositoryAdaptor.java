package com.rollingpaper.ggeujeogggeujeog.board.infrastructure;

import org.springframework.stereotype.Repository;

import com.rollingpaper.ggeujeogggeujeog.board.domain.BoardTagRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardTagRepositoryAdaptor implements BoardTagRepository {

	private final BoardTagMapper boardTagMapper;

	@Override
	public void insertBoardTag(Long boardId, Long tagId) {
		boardTagMapper.insertBoardTag(boardId, tagId);
	}

	@Override
	public void removeBoardTag(Long boardId, Long tagId) {
		boardTagMapper.removeBoardTag(boardId, tagId);
	}
}

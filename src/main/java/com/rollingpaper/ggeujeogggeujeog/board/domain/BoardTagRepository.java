package com.rollingpaper.ggeujeogggeujeog.board.domain;

public interface BoardTagRepository {

	void insertBoardTag(Long boardId, Long tagId);

	void removeBoardTag(Long boardId, Long tagId);
}

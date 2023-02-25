package com.rollingpaper.ggeujeogggeujeog.board.domain;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

	void save(Board board);

	List<Board> findByUserId(Long userId);

	void delete(Long boardId);

	void update(Board board);

	Optional<Board> findById(Long boardId);

	List<Board> findAllBoards(boolean isOpened, int pageSize);

	List<Board> findAllTaggedBoards(List<String> tagNames, boolean isOpened);

}

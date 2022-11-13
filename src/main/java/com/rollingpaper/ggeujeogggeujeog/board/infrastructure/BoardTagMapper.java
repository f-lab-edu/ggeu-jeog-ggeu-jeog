package com.rollingpaper.ggeujeogggeujeog.board.infrastructure;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardTagMapper {

	void insertBoardTag(Long boardId, Long tagId);

	void removeBoardTag(Long boardId, Long tagId);
}

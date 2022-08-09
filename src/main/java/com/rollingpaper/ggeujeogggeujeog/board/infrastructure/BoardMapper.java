package com.rollingpaper.ggeujeogggeujeog.board.infrastructure;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardMapper {
    void save(Board board);
}

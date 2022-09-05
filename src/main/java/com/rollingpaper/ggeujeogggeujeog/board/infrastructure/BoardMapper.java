package com.rollingpaper.ggeujeogggeujeog.board.infrastructure;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Board;

@Repository
@Mapper
public interface BoardMapper {
    void save(Board board);
    List<Board> findByUserId(Long userId);
}

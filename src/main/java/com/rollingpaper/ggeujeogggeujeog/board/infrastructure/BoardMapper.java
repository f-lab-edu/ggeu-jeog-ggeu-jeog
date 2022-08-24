package com.rollingpaper.ggeujeogggeujeog.board.infrastructure;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Board;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.UserBoardResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {
    void save(Board board);

    List<UserBoardResponseDto> findByUserId(Long userId);
}

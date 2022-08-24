package com.rollingpaper.ggeujeogggeujeog.board.application;

import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardRequestDto;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.UserBoardResponseDto;

import java.util.List;

public interface BoardService {
    void register(BoardRequestDto dto, Long id);

    List<UserBoardResponseDto> getUserBoard(Long id);
}

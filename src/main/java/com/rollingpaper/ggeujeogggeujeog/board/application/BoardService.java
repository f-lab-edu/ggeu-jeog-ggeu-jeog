package com.rollingpaper.ggeujeogggeujeog.board.application;

import java.util.List;

import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardRequestDto;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.UserBoardResponseDto;

public interface BoardService {
    void register(BoardRequestDto dto, Long userId);
    List<UserBoardResponseDto> getUserBoard(Long userId);
}

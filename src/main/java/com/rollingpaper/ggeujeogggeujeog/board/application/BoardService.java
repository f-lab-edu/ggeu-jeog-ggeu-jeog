package com.rollingpaper.ggeujeogggeujeog.board.application;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Board;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardRequestDto;

public interface BoardService {
    void register(BoardRequestDto dto, Long id);
}

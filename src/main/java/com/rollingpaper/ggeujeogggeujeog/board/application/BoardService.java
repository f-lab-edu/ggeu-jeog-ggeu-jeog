package com.rollingpaper.ggeujeogggeujeog.board.application;

import java.util.List;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Board;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardRequestDto;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardSearchRequestDto;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardsResponseDto;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.UserBoardResponseDto;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

public interface BoardService {

    void register(BoardRequestDto dto, Long userId);

    List<UserBoardResponseDto> getUserBoard(Long userId);

    void deleteBoard(Long boardId, User user);

    void updateBoard(BoardRequestDto dto, Long boardId, User user);

    Board checkBoardOwner(Long boardId, User user);

    BoardsResponseDto getBoards(boolean isOpened);

    BoardsResponseDto getBoards(BoardSearchRequestDto requestDto);
}

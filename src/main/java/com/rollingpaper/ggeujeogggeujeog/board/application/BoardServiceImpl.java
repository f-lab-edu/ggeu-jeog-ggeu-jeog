package com.rollingpaper.ggeujeogggeujeog.board.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Board;
import com.rollingpaper.ggeujeogggeujeog.board.infrastructure.BoardMapper;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardRequestDto;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.UserBoardResponseDto;
import com.rollingpaper.ggeujeogggeujeog.user.application.UserService;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;
import com.rollingpaper.ggeujeogggeujeog.user.exception.NoSuchUserException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardServiceImpl implements BoardService {

    private final UserService userService;
    private final BoardMapper boardMapper;

    @Override
    public void register(BoardRequestDto dto, Long userId) {
        User user = userService.getUserById(userId).orElseThrow(NoSuchUserException::new);
        boardMapper.save(BoardRequestDto.toEntity(dto, user));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserBoardResponseDto> getUserBoard(Long userId) {
        List<Board> boards = boardMapper.findByUserId(userId);
        return boards.stream()
            .map(board -> new UserBoardResponseDto(board.getBoardTitle(),
            board.getTheme(), board.isOpened(), board.getUpdatedDate()))
            .collect(Collectors.toList());
    }

}

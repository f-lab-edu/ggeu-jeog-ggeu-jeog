package com.rollingpaper.ggeujeogggeujeog.board.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Board;
import com.rollingpaper.ggeujeogggeujeog.board.domain.BoardRepository;
import com.rollingpaper.ggeujeogggeujeog.board.exception.BoardOwnerException;
import com.rollingpaper.ggeujeogggeujeog.board.exception.NoSuchBoardException;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardRequestDto;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardSearchRequestDto;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardsResponseDto;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.UserBoardResponseDto;
import com.rollingpaper.ggeujeogggeujeog.common.exception.BaseException;
import com.rollingpaper.ggeujeogggeujeog.user.application.UserService;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;
import com.rollingpaper.ggeujeogggeujeog.user.exception.NoSuchUserException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final UserService userService;
    private final BoardRepository boardRepository;

    @Override
    public void register(BoardRequestDto dto, Long userId) {
        User user = userService.getUserById(userId).orElseThrow(NoSuchUserException::new);
        boardRepository.save(BoardRequestDto.toEntity(dto, user));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserBoardResponseDto> getUserBoard(Long userId) {
        List<Board> boards = boardRepository.findByUserId(userId);
        return boards.stream()
            .map(board -> new UserBoardResponseDto(board.getBoardTitle(),
            board.getTheme(), board.isOpened(), board.getUpdatedDate()))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteBoard(Long boardId, User user) {
        checkBoardOwner(boardId, user);
        boardRepository.delete(boardId);
    }

    @Override
    @Transactional
    public void updateBoard(BoardRequestDto dto, Long boardId, User user) {
        checkBoardOwner(boardId, user);
        boardRepository.update(BoardRequestDto.toEntity(dto, user));
    }

    @Transactional
    public Board checkBoardOwner(Long boardId, User user) throws BaseException {
        Board board = boardRepository.findById(boardId).orElseThrow(NoSuchBoardException::new);
        if (!board.getUserId().equals(user.getId())) {
            throw new BoardOwnerException();
        }
        return board;
    }

    @Override
    @Transactional(readOnly = true)
    public BoardsResponseDto getBoards(boolean isOpened) {
        List<Board> boards = boardRepository.findAllBoards(isOpened);
        return new BoardsResponseDto(boards);
    }

    @Override
    @Transactional(readOnly = true)
    public BoardsResponseDto getBoards(BoardSearchRequestDto dto) {
        List<Board> boards = boardRepository.findAllTaggedBoards(dto.getTagNames(), dto.isOpened());
        return new BoardsResponseDto(boards);
    }
}

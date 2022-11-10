package com.rollingpaper.ggeujeogggeujeog.board.presentation;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.CurrentUser;
import com.rollingpaper.ggeujeogggeujeog.board.application.BoardService;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardRequestDto;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardsResponseDto;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.UserBoardResponseDto;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<Void> write(
            @RequestBody @Valid BoardRequestDto dto,
            @CurrentUser User user
    ) {
        boardService.register(dto, user.getId());
        return ResponseEntity.status(OK).build();
    }

    @GetMapping("/boards")
    public ResponseEntity<List<UserBoardResponseDto>> getUserBoard(
        @CurrentUser User user
    ) {
        List<UserBoardResponseDto> userBoard = boardService.getUserBoard(user.getId());
        return ResponseEntity.ok(userBoard);
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<Void> deleteBoard(
        @PathVariable Long boardId,
        @CurrentUser User user
    ) {
        boardService.deleteBoard(boardId, user);
        return ResponseEntity.status(OK).build();
    }

    @PatchMapping("/boards/{boardId}")
    public ResponseEntity<Void> updateBoard(
        @PathVariable Long boardId,
        @RequestBody @Valid BoardRequestDto dto,
        @CurrentUser User user
    ) {
        boardService.updateBoard(dto, boardId, user);
        return ResponseEntity.status(OK).build();
    }

    @GetMapping("/boards/open")
    public ResponseEntity<BoardsResponseDto> getOpenedBoards(
        @RequestParam(required = false, value = "opened", defaultValue = "true") boolean isOpened
    ) {
        BoardsResponseDto dto = boardService.getBoards(isOpened);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/boards/search")
    public ResponseEntity<BoardsResponseDto> searchOpenedBoards(
        @RequestParam(required = false, value = "tagList") List<String> tagNames,
        @RequestParam(required = false, value = "opened", defaultValue = "true") boolean isOpened
    ) {
        BoardsResponseDto dto = boardService.getBoards(tagNames, isOpened);
        return ResponseEntity.ok(dto);
    }
}

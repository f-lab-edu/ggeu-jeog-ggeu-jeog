package com.rollingpaper.ggeujeogggeujeog.board.presentation;

import com.rollingpaper.ggeujeogggeujeog.board.application.BoardService;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardRequestDto;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.UserBoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequestMapping("/api/users")
@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/{id}/boards")
    public ResponseEntity<Void> write(
            @RequestBody @Valid BoardRequestDto dto,
            @PathVariable Long id
    ) {
        boardService.register(dto, id);
        return ResponseEntity.status(OK).build();
    }

    @GetMapping("/{id}/boards")
    public ResponseEntity<List<UserBoardResponseDto>> getUserBoard(
            @PathVariable Long id
    ) {
        List<UserBoardResponseDto> userBoard = boardService.getUserBoard(id);
        return ResponseEntity.ok(userBoard);
    }
}

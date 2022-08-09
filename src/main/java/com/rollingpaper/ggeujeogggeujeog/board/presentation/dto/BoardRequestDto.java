package com.rollingpaper.ggeujeogggeujeog.board.presentation.dto;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Board;
import com.rollingpaper.ggeujeogggeujeog.board.domain.Theme;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class BoardRequestDto {
    @NotBlank
    private String boardTitle;

    private Theme theme;

    private boolean isOpened;

    @Builder
    public BoardRequestDto(String boardTitle, Theme theme, boolean isOpened) {
        this.boardTitle = boardTitle;
        this.theme = theme;
        this.isOpened = isOpened;
    }

    public static Board toEntity(BoardRequestDto dto, Long userId) {
        return Board.builder()
                .boardTitle(dto.boardTitle)
                .isOpened(dto.isOpened)
                .theme(dto.theme)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .userId(userId)
                .build();
    }
}

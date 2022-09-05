package com.rollingpaper.ggeujeogggeujeog.board.presentation.dto;

import java.time.LocalDateTime;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Theme;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserBoardResponseDto {

    private String boardTitle;

    private Theme theme;

    private boolean isOpened;

    private LocalDateTime updatedDate;

    @Builder
    public UserBoardResponseDto(String boardTitle, Theme theme, boolean isOpened, LocalDateTime updatedDate) {
        this.boardTitle = boardTitle;
        this.theme = theme;
        this.isOpened = isOpened;
        this.updatedDate = updatedDate;
    }
}

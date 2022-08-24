package com.rollingpaper.ggeujeogggeujeog.board.presentation.dto;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Theme;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class UserBoardResponseDto {

    private String boardTitle;

    private Theme theme;

    private boolean isOpened;

    private String updatedTime;

    @Builder
    public UserBoardResponseDto(String boardTitle, Theme theme, boolean isOpened, LocalDateTime updatedTime) {
        this.boardTitle = boardTitle;
        this.theme = theme;
        this.isOpened = isOpened;
        this.updatedTime = updatedTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}

package com.rollingpaper.ggeujeogggeujeog.board.presentation.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Board;
import com.rollingpaper.ggeujeogggeujeog.board.domain.Theme;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardRequestDto {
    @NotBlank
    @Pattern(
            regexp = "^[0-9\\s]|[a-zA-Z\\s]|[ㄱ-ㅎ가-힣\\s]|[ㄱ-ㅎ가-힣a-zA-Z\\s]{1,50}$",
            message = "숫자와 문자 중 하나의 조합으로 최소 1자 최대 50자로 입력해주세요."
    )
    private String boardTitle;

    private Theme theme;

    private Boolean isOpened;

    @Builder
    public BoardRequestDto(String boardTitle, Theme theme, Boolean isOpened) {
        this.theme = Objects.requireNonNullElse(theme, Theme.THEME1);
        this.boardTitle = boardTitle;
        this.isOpened = Objects.requireNonNullElse(isOpened, false);
    }

    public static Board toEntity(BoardRequestDto dto, User user) {
        return Board.builder()
                .boardTitle(dto.boardTitle)
                .isOpened(dto.isOpened)
                .theme(dto.theme)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .userId(user.getId())
                .build();
    }
}

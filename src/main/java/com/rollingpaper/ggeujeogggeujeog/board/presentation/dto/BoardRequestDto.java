package com.rollingpaper.ggeujeogggeujeog.board.presentation.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Board;
import com.rollingpaper.ggeujeogggeujeog.board.domain.Theme;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestDto {
    @NotBlank
    @Pattern(
            regexp = "^[a-zA-Z\\d$@$!%*?&]{1,50}$",
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
                .userId(user.getId())
                .build();
    }
}

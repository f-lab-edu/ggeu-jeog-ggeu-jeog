package com.rollingpaper.ggeujeogggeujeog.board.application;

import static com.rollingpaper.ggeujeogggeujeog.common.fixture.UserTestFixture.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Theme;
import com.rollingpaper.ggeujeogggeujeog.board.infrastructure.BoardMapper;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardRequestDto;
import com.rollingpaper.ggeujeogggeujeog.user.application.UserService;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.SignUpRequestDto;

@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest {

    @InjectMocks
    private BoardServiceImpl boardService;

    @Mock
    private BoardMapper boardMapper;

    @Mock
    private UserService userService;

    @Test
    @DisplayName("서비스의 회원인 사용자는 보드 등록에 성공한다.")
    void register() {

        SignUpRequestDto userDto = SignUpRequestDto.builder()
                .email(TestUser.USER1.getEmail())
                .password(TestUser.USER1.getPassword())
                .nickname(TestUser.USER1.getNickname())
                .build();

        given(userService.getUserById(1L)).willReturn(Optional.of(TestUser.USER1));
        userService.register(userDto);

        BoardRequestDto boardDto = BoardRequestDto.builder()
                .boardTitle("Test Board Title")
                .theme(Theme.THEME1)
                .isOpened(true)
                .build();

        boardService.register(boardDto, TestUser.USER1.getId());

        then(boardMapper).should(times(1)).save(any());
        then(boardMapper).shouldHaveNoMoreInteractions();
    }
}
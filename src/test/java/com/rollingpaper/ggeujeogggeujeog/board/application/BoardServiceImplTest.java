package com.rollingpaper.ggeujeogggeujeog.board.application;

import static com.rollingpaper.ggeujeogggeujeog.common.fixture.BoardTestFixture.*;
import static com.rollingpaper.ggeujeogggeujeog.common.fixture.UserTestFixture.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Theme;
import com.rollingpaper.ggeujeogggeujeog.board.exception.BoardOwnerException;
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

    @Test
    @DisplayName("소유자가 아닐 경우 업데이트를 실패한다.")
    void updateBoardNotMine() {
        //given
        BoardRequestDto dto = BoardRequestDto.builder()
            .boardTitle(TestBoard.BOARD1.getBoardTitle())
            .theme(TestBoard.BOARD1.getTheme())
            .isOpened(TestBoard.BOARD1.isOpened())
            .build();
        given(boardMapper.findById(anyLong())).willReturn(Optional.of(TestBoard.BOARD1));
        given(boardService.checkBoardOwner(eq(1L), TestUser.USER1)).willThrow(BoardOwnerException.class);

        //then
        Assertions.assertThrows(
            BoardOwnerException.class,
            () -> boardService.updateBoard(dto, TestBoard.BOARD1.getId(), TestUser.USER2)
        );
    }

    @Test
    @DisplayName("소유자가 아닐 경우 보드를 삭제할 수 없다.")
    void deleteBoardNotMine() {
        //given
        given(boardMapper.findById(anyLong())).willReturn(Optional.of(TestBoard.BOARD1));
        given(boardService.checkBoardOwner(eq(1L), TestUser.USER1)).willThrow(BoardOwnerException.class);

        //then
        Assertions.assertThrows(
            BoardOwnerException.class,
            () -> boardService.deleteBoard(TestBoard.BOARD1.getId(), TestUser.USER2)
        );
    }

    @Test
    @DisplayName("소유자가 보드를 삭제한다.")
    void deleteMyBoard() {
        //given
        given(boardMapper.findById(anyLong())).willReturn(Optional.ofNullable(TestBoard.BOARD1));

        //when
        boardService.deleteBoard(TestBoard.BOARD1.getId(), TestUser.USER1);

        //then
        then(boardMapper).should(times(1)).delete(anyLong());
    }

    @Test
    @DisplayName("소유자가 보드를 수정한다.")
    void updateMyBoard() {
        //given
        BoardRequestDto dto = BoardRequestDto.builder()
            .boardTitle(TestBoard.BOARD1.getBoardTitle())
            .theme(TestBoard.BOARD1.getTheme())
            .isOpened(TestBoard.BOARD1.isOpened())
            .build();
        given(boardMapper.findById(anyLong())).willReturn(Optional.ofNullable(TestBoard.BOARD1));

        //when
        boardService.updateBoard(dto, TestBoard.BOARD1.getId(), TestUser.USER1);

        //then
        then(boardMapper).should(times(1)).update(any(), anyLong());
    }
}
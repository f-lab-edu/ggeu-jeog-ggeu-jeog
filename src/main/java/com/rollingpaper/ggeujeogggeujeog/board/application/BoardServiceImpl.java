package com.rollingpaper.ggeujeogggeujeog.board.application;

import com.rollingpaper.ggeujeogggeujeog.board.infrastructure.BoardMapper;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardRequestDto;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.UserBoardResponseDto;
import com.rollingpaper.ggeujeogggeujeog.user.Exception.NoSuchUserException;
import com.rollingpaper.ggeujeogggeujeog.user.application.UserService;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardServiceImpl implements BoardService {

    private final UserService userService;
    private final BoardMapper boardMapper;

    @Override
    public void register(BoardRequestDto dto, Long id) {
        Optional<User> user = userService.getUserById(id);
        boardMapper.save(BoardRequestDto.toEntity(dto, user.orElseThrow(NoSuchUserException::new).getId()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserBoardResponseDto> getUserBoard(Long id) {
        userService.getUserById(id)
                .orElseThrow(NoSuchUserException::new);

        return boardMapper.findByUserId(id);

    }

}

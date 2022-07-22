package com.rollingpaper.ggeujeogggeujeog.user.application;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.rollingpaper.ggeujeogggeujeog.common.constant.SessionConst;
import com.rollingpaper.ggeujeogggeujeog.common.util.PasswordEncoder;
import com.rollingpaper.ggeujeogggeujeog.user.Exception.DuplicatedEmailException;
import com.rollingpaper.ggeujeogggeujeog.user.Exception.NoSuchUserException;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;
import com.rollingpaper.ggeujeogggeujeog.user.infrastructure.UserMapper;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.SignInRequestDto;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.SignUpRequestDto;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.UserProfileResponseDto;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.UserUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserMapper userMapper;
	private final HttpSession httpSession;
	private final PasswordEncoder passwordEncoder;

	public void register(SignUpRequestDto dto) {
		userMapper.findByEmail(dto.getEmail())
				.ifPresent(user -> {
					throw new DuplicatedEmailException();
				});
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		userMapper.save(SignUpRequestDto.toEntity(dto));
	}


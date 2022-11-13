package com.rollingpaper.ggeujeogggeujeog.user.application;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rollingpaper.ggeujeogggeujeog.authentication.application.LoginService;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.UserUpdateRequestDto;
import com.rollingpaper.ggeujeogggeujeog.common.util.PasswordEncoder;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;
import com.rollingpaper.ggeujeogggeujeog.user.exception.NoSuchUserException;
import com.rollingpaper.ggeujeogggeujeog.user.infrastructure.UserMapper;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.UserProfileResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final LoginService loginService;

	@Transactional
	public void update(UserUpdateRequestDto dto, Long id) {
		User user = userMapper.findById(id)
			.orElseThrow(NoSuchUserException::new);
		user.updateProfile(dto.getNickname(), passwordEncoder.encode(dto.getPassword()));
		userMapper.update(user);
	}

	@Transactional
	public void delete(Long userId) {
		User user = userMapper.findById(userId)
			.orElseThrow(NoSuchUserException::new);
		userMapper.delete(user.getId());
		loginService.signOut();
	}

	@Transactional(readOnly = true)
	public UserProfileResponseDto getUserProfile(Long id) {
		User user = userMapper.findById(id)
			.orElseThrow(NoSuchUserException::new);
		return UserProfileResponseDto.from(user);
	}

	@Transactional(readOnly = true)
	public Optional<User> getUserById(Long id) {
		return userMapper.findById(id);
	}
}

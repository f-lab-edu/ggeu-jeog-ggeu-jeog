package com.rollingpaper.ggeujeogggeujeog.user.application;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rollingpaper.ggeujeogggeujeog.authentication.application.LoginService;
import com.rollingpaper.ggeujeogggeujeog.common.util.PasswordEncoder;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;
import com.rollingpaper.ggeujeogggeujeog.user.exception.DuplicatedEmailException;
import com.rollingpaper.ggeujeogggeujeog.user.exception.NoSuchUserException;
import com.rollingpaper.ggeujeogggeujeog.user.infrastructure.UserMapper;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.SignUpRequestDto;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.UserProfileResponseDto;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.UserUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserMapper userMapper;
	private final LoginService loginService;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public void register(SignUpRequestDto dto) {
		userMapper.findByEmail(dto.getEmail())
				.ifPresent(user -> {
					throw new DuplicatedEmailException();
				});
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		userMapper.save(SignUpRequestDto.toEntity(dto));
	}

	@Transactional
	public void update(UserUpdateRequestDto dto, Long id) {
		User user = userMapper.findById(id)
			.orElseThrow(NoSuchUserException::new);
		user.updateProfile(dto.getNickname(), passwordEncoder.encode(dto.getPassword()));
		userMapper.update(user);
	}

	@Transactional
	public void delete(Long userId) {
		userMapper.delete(userId);
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

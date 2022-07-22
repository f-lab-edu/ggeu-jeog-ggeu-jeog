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

	public void signIn(SignInRequestDto dto) {
		Optional<User> userOptional = userMapper.findByEmail(dto.getEmail());
		userOptional.orElseThrow(NoSuchUserException::new);
		User user = userOptional.filter(
				u -> passwordEncoder.matches(dto.getPassword(), u.getPassword()))
			.orElseThrow(() -> new IllegalArgumentException("패스워드가 다릅니다."));
		httpSession.setAttribute(SessionConst.USER_ID, user);
	}

	public void signOut() {
		httpSession.removeAttribute(SessionConst.USER_ID);
	}

	public void update(UserUpdateRequestDto dto, Long id) {
		User user = userMapper.findById(id)
			.orElseThrow(NoSuchUserException::new);
		user.updateProfile(dto.getNickname(), passwordEncoder.encode(dto.getPassword()));
		userMapper.update(user);
		httpSession.setAttribute(SessionConst.USER_ID, user);
	}

	public void delete(Long userId) {
		userMapper.delete(userId);
		httpSession.removeAttribute(SessionConst.USER_ID);
	}

	public UserProfileResponseDto getUserProfile(Long id) {
		User user = userMapper.findById(id)
			.orElseThrow(NoSuchUserException::new);
		return UserProfileResponseDto.from(user);
	}
}

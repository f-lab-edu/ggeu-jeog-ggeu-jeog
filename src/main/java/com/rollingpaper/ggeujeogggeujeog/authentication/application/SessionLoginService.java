package com.rollingpaper.ggeujeogggeujeog.authentication.application;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rollingpaper.ggeujeogggeujeog.common.constant.SessionConst;
import com.rollingpaper.ggeujeogggeujeog.common.util.PasswordEncoder;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;
import com.rollingpaper.ggeujeogggeujeog.user.exception.NoSuchUserException;
import com.rollingpaper.ggeujeogggeujeog.user.infrastructure.UserMapper;
import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.dto.SignInRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SessionLoginService implements LoginService {

	private final HttpSession httpSession;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public void signIn(SignInRequestDto dto) {
		Optional<User> userOptional = userMapper.findByEmail(dto.getEmail());
		userOptional.orElseThrow(NoSuchUserException::new);
		User user = userOptional.filter(
				u -> passwordEncoder.matches(dto.getPassword(), u.getPassword()))
			.orElseThrow(() -> new IllegalArgumentException("패스워드가 다릅니다."));
		httpSession.setAttribute(SessionConst.USER_ID, user.getId());
	}

	@Override
	public void signOut() {
		httpSession.removeAttribute(SessionConst.USER_ID);
	}

	@Override
	public Long getUserId() {
		return (Long) httpSession.getAttribute(SessionConst.USER_ID);
	}
}

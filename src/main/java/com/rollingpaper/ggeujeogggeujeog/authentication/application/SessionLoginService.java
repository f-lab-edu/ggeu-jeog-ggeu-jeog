package com.rollingpaper.ggeujeogggeujeog.authentication.application;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.dto.SignInRequestDto;
import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.dto.SignUpRequestDto;
import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.dto.VerifyEmailRequestDto;
import com.rollingpaper.ggeujeogggeujeog.common.constant.SessionConst;
import com.rollingpaper.ggeujeogggeujeog.common.util.PasswordEncoder;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;
import com.rollingpaper.ggeujeogggeujeog.user.exception.DuplicatedEmailException;
import com.rollingpaper.ggeujeogggeujeog.user.exception.NoSuchUserException;
import com.rollingpaper.ggeujeogggeujeog.user.infrastructure.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class SessionLoginService implements LoginService {

	private final HttpSession httpSession;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final EmailVerificationService emailVerificationService;

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

	@Override
	@Transactional
	public void register(SignUpRequestDto dto) {
		userMapper.findByEmail(dto.getEmail())
			.ifPresent(user -> {
				throw new DuplicatedEmailException();
			});
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		userMapper.save(SignUpRequestDto.toEntity(dto));
		String uuid = UUID.randomUUID().toString();
		log.debug("{} {} has been created", dto.getEmail(), uuid);
		emailVerificationService.sendRegistrationMail(dto.getEmail(), uuid);
	}

	@Override
	@Transactional
	public void confirmRegistration(VerifyEmailRequestDto dto) {
		emailVerificationService.verify(dto.getEmail(), dto.getToken());
		User user = userMapper.findByEmail(dto.getEmail())
			.orElseThrow(NoSuchUserException::new);
		user.verifiedUser();
		userMapper.update(user);
	}
}

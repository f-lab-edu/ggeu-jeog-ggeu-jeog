package com.rollingpaper.ggeujeogggeujeog.authentication.presentation;

import static org.springframework.http.HttpStatus.*;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rollingpaper.ggeujeogggeujeog.authentication.application.LoginService;
import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.dto.SignInRequestDto;
import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.dto.SignUpRequestDto;
import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.dto.VerifyEmailRequestDto;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@RestController
public class AuthController {

	private final LoginService loginService;

	@PostMapping("/sign-in")
	public ResponseEntity<Void> signIn(
		@RequestBody @Valid SignInRequestDto dto
	) {
		loginService.signIn(dto);
		return ResponseEntity.status(OK).build();
	}

	@GetMapping("/sign-out")
	public ResponseEntity<Void> signOut() {
		loginService.signOut();
		return ResponseEntity.status(OK).build();
	}

	@PostMapping("/sign-up")
	public ResponseEntity<Void> signUp(
		@RequestBody @Valid SignUpRequestDto dto
	) {
		loginService.register(dto);
		return ResponseEntity.status(OK).build();
	}

	@PostMapping("/authentication")
	public ResponseEntity<Void> confirmRegistration(
		@RequestBody @Valid VerifyEmailRequestDto dto
	) {
		loginService.confirmRegistration(dto);
		return ResponseEntity.status(OK).build();
	}
}

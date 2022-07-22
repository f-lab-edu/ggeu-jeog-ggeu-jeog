package com.rollingpaper.ggeujeogggeujeog.user.presentation;

import static org.springframework.http.HttpStatus.*;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rollingpaper.ggeujeogggeujeog.user.application.UserService;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.SignInRequestDto;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.SignUpRequestDto;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.UserProfileResponseDto;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.UserUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/users")
@RequiredArgsConstructor
@RestController
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<Void> signUp(
		@RequestBody @Valid SignUpRequestDto dto
	) {
		userService.register(dto);
		return ResponseEntity.status(OK).build();
	}

	@PostMapping("/sign-in")
	public ResponseEntity<Void> signIn(
		@RequestBody @Valid SignInRequestDto dto
	) {
		userService.signIn(dto);
		return ResponseEntity.status(OK).build();
	}

	@GetMapping("/sign-out")
	public ResponseEntity<Void> signOut() {
		userService.signOut();
		return ResponseEntity.status(OK).build();
	}


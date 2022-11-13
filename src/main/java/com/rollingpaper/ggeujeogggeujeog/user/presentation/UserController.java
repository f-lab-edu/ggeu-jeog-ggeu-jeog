package com.rollingpaper.ggeujeogggeujeog.user.presentation;

import static org.springframework.http.HttpStatus.*;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.UserUpdateRequestDto;
import com.rollingpaper.ggeujeogggeujeog.user.application.UserService;
import com.rollingpaper.ggeujeogggeujeog.user.presentation.dto.UserProfileResponseDto;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@RestController
public class UserController {

	private final UserService userService;

	@PatchMapping("/{id}")
	public ResponseEntity<Void> update(
		@RequestBody @Valid UserUpdateRequestDto dto,
		@PathVariable Long id
	) {
		userService.update(dto, id);
		return ResponseEntity.status(OK).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(
		@PathVariable Long id
	) {
		userService.delete(id);
		return ResponseEntity.status(OK).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserProfileResponseDto> getUserProfile(
		@PathVariable Long id
	) {
		UserProfileResponseDto responseDto = userService.getUserProfile(id);
		return ResponseEntity.ok(responseDto);
	}
}

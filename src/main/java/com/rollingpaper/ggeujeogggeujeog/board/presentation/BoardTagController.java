package com.rollingpaper.ggeujeogggeujeog.board.presentation;

import static org.springframework.http.HttpStatus.*;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.CurrentUser;
import com.rollingpaper.ggeujeogggeujeog.board.application.BoardTagService;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardTagRequestDto;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class BoardTagController {

	private final BoardTagService boardTagService;

	@PostMapping("/boards/{boardId}/tags")
	public ResponseEntity<Void> addBoardTags(
		@RequestBody @Valid BoardTagRequestDto dto,
		@PathVariable Long boardId,
		@CurrentUser User user
	) {
		boardTagService.addBoardTags(dto, boardId, user);
		return ResponseEntity.status(OK).build();
	}

	@DeleteMapping("/boards/{boardId}/tags")
	public ResponseEntity<Void> deleteBoardTags(
		@RequestBody @Valid BoardTagRequestDto dto,
		@PathVariable Long boardId,
		@CurrentUser User user
	) {
		boardTagService.removeBoardTags(dto, boardId, user);
		return ResponseEntity.status(OK).build();
	}
}

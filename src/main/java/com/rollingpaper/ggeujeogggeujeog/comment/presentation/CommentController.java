package com.rollingpaper.ggeujeogggeujeog.comment.presentation;

import static org.springframework.http.HttpStatus.*;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.CurrentUser;
import com.rollingpaper.ggeujeogggeujeog.comment.application.CommentService;
import com.rollingpaper.ggeujeogggeujeog.comment.presentation.dto.CommentResponseDto;
import com.rollingpaper.ggeujeogggeujeog.comment.presentation.dto.CommentWriteRequestDto;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/rollingpapers/{paperId}/comment")
	public ResponseEntity<Void> writeComment(
		@RequestBody @Valid CommentWriteRequestDto dto,
		@PathVariable Long paperId,
		@CurrentUser User user
	) {
		commentService.writeComment(dto, paperId, user.getId());
		return ResponseEntity.status(OK).build();
	}

	@GetMapping("/rollingpapers/{paperId}/comment")
	public ResponseEntity<CommentResponseDto> getComment(
		@PathVariable Long paperId
	) {
		CommentResponseDto commentResponseDto = commentService.getCommentByPaperId(paperId);
		return ResponseEntity.ok(commentResponseDto);
	}
}

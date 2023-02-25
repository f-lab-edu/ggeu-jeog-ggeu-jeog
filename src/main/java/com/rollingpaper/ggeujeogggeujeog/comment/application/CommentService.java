package com.rollingpaper.ggeujeogggeujeog.comment.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rollingpaper.ggeujeogggeujeog.board.application.BoardService;
import com.rollingpaper.ggeujeogggeujeog.comment.domain.Comment;
import com.rollingpaper.ggeujeogggeujeog.comment.domain.CommentRepository;
import com.rollingpaper.ggeujeogggeujeog.comment.exception.CommentAlreadyExistException;
import com.rollingpaper.ggeujeogggeujeog.comment.exception.NoSuchCommentException;
import com.rollingpaper.ggeujeogggeujeog.comment.presentation.dto.CommentResponseDto;
import com.rollingpaper.ggeujeogggeujeog.comment.presentation.dto.CommentWriteRequestDto;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final BoardService boardService;

	@Transactional
	public void writeComment(CommentWriteRequestDto dto, Long boardId, Long paperId, User user) {
		boardService.checkBoardOwner(boardId, user);
		if(commentRepository.findByPaperId(paperId).isPresent()) {
			throw new CommentAlreadyExistException();
		}
		commentRepository.save(dto.toEntity(dto, paperId, user.getId()));
	}

	@Transactional(readOnly = true)
	public CommentResponseDto getCommentByPaperId(Long paperId) {
		Comment comment = commentRepository.findByPaperId(paperId)
			.orElseThrow(NoSuchCommentException::new);
		return CommentResponseDto.from(comment);
	}

	@Transactional(readOnly = true)
	public List<CommentResponseDto> getCommentsByUser(Long userId, int page) {
		return commentRepository.findAllByUserId(userId, page)
			.stream()
			.map(comment -> new CommentResponseDto(
				comment.getContent(),
				comment.getCreatedDate(),
				comment.getUpdatedDate()))
			.collect(Collectors.toList());
	}

	@Transactional
	public void deleteComment(Long boardId, Long paperId, User user) {
		commentRepository.findByPaperId(paperId).orElseThrow(NoSuchCommentException::new);
		boardService.checkBoardOwner(boardId, user);
		commentRepository.delete(paperId);
	}

	@Transactional
	public void updateComment(CommentWriteRequestDto dto, Long boardId, Long paperId, User user) {
		boardService.checkBoardOwner(boardId, user);
		commentRepository.update(CommentWriteRequestDto.toEntity(dto, paperId, user.getId()));
	}
}

package com.rollingpaper.ggeujeogggeujeog.comment.presentation.dto;

import java.time.LocalDateTime;

import com.rollingpaper.ggeujeogggeujeog.comment.domain.Comment;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponseDto {

	private String content;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

	@Builder
	public CommentResponseDto(String content, LocalDateTime createdDate, LocalDateTime updatedDate) {
		this.content = content;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public static CommentResponseDto from(Comment comment) {
		return CommentResponseDto.builder()
			.content(comment.getContent())
			.createdDate(comment.getCreatedDate())
			.updatedDate(comment.getUpdatedDate())
			.build();
	}
}

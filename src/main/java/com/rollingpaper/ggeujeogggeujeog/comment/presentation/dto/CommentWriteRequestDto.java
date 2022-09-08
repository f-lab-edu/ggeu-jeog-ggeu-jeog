package com.rollingpaper.ggeujeogggeujeog.comment.presentation.dto;

import javax.validation.constraints.NotBlank;

import com.rollingpaper.ggeujeogggeujeog.comment.domain.Comment;

import lombok.Getter;

@Getter
public class CommentWriteRequestDto {

	@NotBlank
	private String content;

	public CommentWriteRequestDto(String content) {
		this.content = content;
	}

	public static Comment toEntity(CommentWriteRequestDto dto, Long paperId, Long userId) {
		return Comment.builder()
			.content(dto.content)
			.paperId(paperId)
			.userId(userId)
			.build();
	}
}

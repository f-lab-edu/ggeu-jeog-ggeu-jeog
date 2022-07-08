package com.rollingpaper.ggeujeogggeujeog.comment.domain;

import java.time.LocalDateTime;

import com.rollingpaper.ggeujeogggeujeog.paper.domain.Paper;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Comment {
	private Long id;
	private String content;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private User userId;
	private Paper paperId;

	@Builder
	public Comment(Long id, String content, LocalDateTime createdDate, LocalDateTime updatedDate,
		User userId, Paper paperId) {
		this.id = id;
		this.content = content;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.userId = userId;
		this.paperId = paperId;
	}
}

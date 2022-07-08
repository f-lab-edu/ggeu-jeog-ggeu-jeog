package com.rollingpaper.ggeujeogggeujeog.paper.domain;

import java.time.LocalDateTime;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Board;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Paper {
	private Long id;
	private String ownerName;
	private String content;
	private String contentMeta;
	private String imageUrl;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private User userId;
	private Board boardId;

	@Builder
	public Paper(Long id, String ownerName, String content, String contentMeta, String imageUrl,
		LocalDateTime createdDate, LocalDateTime updatedDate, User userId,
		Board boardId) {
		this.id = id;
		this.ownerName = ownerName;
		this.content = content;
		this.contentMeta = contentMeta;
		this.imageUrl = imageUrl;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.userId = userId;
		this.boardId = boardId;
	}
}

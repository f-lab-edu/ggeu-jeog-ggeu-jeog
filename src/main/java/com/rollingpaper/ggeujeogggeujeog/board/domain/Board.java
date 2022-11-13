package com.rollingpaper.ggeujeogggeujeog.board.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Board {
	private Long id;
	private String boardTitle;
	private Theme theme;
	private boolean isOpened;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private Long userId;
	private List<Tag> tags;

	@Builder
	public Board(Long id, String boardTitle, Theme theme, boolean isOpened, LocalDateTime createdDate,
		LocalDateTime updatedDate, Long userId, List<Tag> tags) {
		this.id = id;
		this.boardTitle = boardTitle;
		this.theme = theme;
		this.isOpened = isOpened;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.userId = userId;
		this.tags = tags;
	}

	void addTag(Tag tag) {
		this.tags.add(tag);
	}
}

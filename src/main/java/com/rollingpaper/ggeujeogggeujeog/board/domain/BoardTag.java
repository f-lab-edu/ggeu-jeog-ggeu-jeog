package com.rollingpaper.ggeujeogggeujeog.board.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardTag {

	private Long id;
	private Long boardId;
	private Long tagId;

	@Builder
	public BoardTag(Long id, Long boardId, Long tagId) {
		this.id = id;
		this.boardId = boardId;
		this.tagId = tagId;
	}
}

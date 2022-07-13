package com.rollingpaper.ggeujeogggeujeog.board.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Tag {
	private Long id;
	private	Long tagInfoId;
	private Long boardId;

	public Tag(Long id, Long tagInfoId, Long boardId) {
		this.id = id;
		this.tagInfoId = tagInfoId;
		this.boardId = boardId;
	}
}

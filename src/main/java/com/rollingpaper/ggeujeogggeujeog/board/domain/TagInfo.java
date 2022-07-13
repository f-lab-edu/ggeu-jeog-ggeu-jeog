package com.rollingpaper.ggeujeogggeujeog.board.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TagInfo {
	private Long id;
	private String name;

	public TagInfo(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}

package com.rollingpaper.ggeujeogggeujeog.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Tag {
	private Long id;
	private String name;

	public Tag(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public static Tag createTagUsingName(String name) {
		return new Tag(null, name);
	}
}

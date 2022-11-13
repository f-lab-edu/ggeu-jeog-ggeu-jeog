package com.rollingpaper.ggeujeogggeujeog.board.presentation.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSearchRequestDto {

	private List<String> tagNames;
	private boolean isOpened;

	public BoardSearchRequestDto(List<String> tagNames, boolean isOpened) {
		this.tagNames = tagNames;
		this.isOpened = isOpened;
	}
}

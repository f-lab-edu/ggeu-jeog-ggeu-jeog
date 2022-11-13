package com.rollingpaper.ggeujeogggeujeog.board.presentation.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardTagRequestDto {

	private List<@NotNull @NotEmpty @Size(min = 1, max=10) String> tagNames;

	public BoardTagRequestDto(List<String> tagNames) {
		this.tagNames = tagNames;
	}
}

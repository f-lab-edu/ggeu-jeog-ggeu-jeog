package com.rollingpaper.ggeujeogggeujeog.board.presentation.dto;

import java.util.List;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardsResponseDto {

	private List<Board> boardList;

	public BoardsResponseDto(List<Board> boardList) {
		this.boardList = boardList;
	}

	int getSize() {
		return boardList.size();
	}

	@Override
	public String toString() {
		return "BoardsResponseDto{" +
			"boardList=" + boardList +
			'}';
	}
}

package com.rollingpaper.ggeujeogggeujeog.common.fixture;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Board;
import com.rollingpaper.ggeujeogggeujeog.board.domain.Theme;

public class BoardTestFixture {

	public static class TestBoard {
		public static Board BOARD1 = Board.builder()
			.id(1L)
			.boardTitle("test-title-1")
			.theme(Theme.THEME1)
			.isOpened(false)
			.userId(UserTestFixture.TestUser.USER1.getId())
			.build();

		public static Board BOARD2 = Board.builder()
			.id(2L)
			.boardTitle("test-title-2")
			.theme(Theme.THEME1)
			.isOpened(false)
			.userId(UserTestFixture.TestUser.USER2.getId())
			.build();
	}
}

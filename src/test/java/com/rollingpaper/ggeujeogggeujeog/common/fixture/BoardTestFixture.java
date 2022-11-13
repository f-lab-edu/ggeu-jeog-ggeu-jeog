package com.rollingpaper.ggeujeogggeujeog.common.fixture;

import static com.rollingpaper.ggeujeogggeujeog.common.fixture.TagTestFixture.TestTag.*;

import java.util.Arrays;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Board;
import com.rollingpaper.ggeujeogggeujeog.board.domain.Theme;

public class BoardTestFixture {

	public static class TestBoard {
		public static Board BOARD1 = Board.builder()
			.id(1L)
			.boardTitle("Title1")
			.theme(Theme.THEME1)
			.isOpened(false)
			.userId(UserTestFixture.TestUser.USER1.getId())
			.tags(Arrays.asList(TAG1, TAG2))
			.build();

		public static Board BOARD2 = Board.builder()
			.id(2L)
			.boardTitle("Title2")
			.theme(Theme.THEME1)
			.isOpened(false)
			.userId(UserTestFixture.TestUser.USER2.getId())
			.tags(Arrays.asList(TAG1))
			.build();

		public static Board BOARD3 = Board.builder()
			.id(2L)
			.boardTitle("Title2")
			.theme(Theme.THEME1)
			.isOpened(true)
			.userId(UserTestFixture.TestUser.USER2.getId())
			.tags(Arrays.asList(TAG1))
			.build();

		public static Board BOARD4 = Board.builder()
			.id(2L)
			.boardTitle("Title2")
			.theme(Theme.THEME1)
			.isOpened(true)
			.userId(UserTestFixture.TestUser.USER2.getId())
			.tags(Arrays.asList(TAG2))
			.build();
	}
}

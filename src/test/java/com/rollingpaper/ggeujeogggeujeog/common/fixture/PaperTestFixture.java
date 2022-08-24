package com.rollingpaper.ggeujeogggeujeog.common.fixture;

import java.time.LocalDateTime;

import com.rollingpaper.ggeujeogggeujeog.paper.domain.Paper;

public class PaperTestFixture {

	public static class TestPaper {
		public static Paper PAPER1 = Paper.builder()
			.id(1L)
			.ownerName("test-owner-1")
			.content("test-content")
			.contentMeta("{meta:\"test\"")
			.imageUrl(ImageTestFixture.IMAGE_NAME1)
			.createdDate(LocalDateTime.now())
			.updatedDate(LocalDateTime.now())
			.userId(UserTestFixture.TestUser.USER1.getId())
			.boardId(BoardTestFixture.TestBoard.BOARD1.getId())
			.build();
	}
}

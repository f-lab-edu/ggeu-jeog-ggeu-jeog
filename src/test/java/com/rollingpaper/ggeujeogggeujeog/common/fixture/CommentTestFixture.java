package com.rollingpaper.ggeujeogggeujeog.common.fixture;

import java.time.LocalDateTime;

import com.rollingpaper.ggeujeogggeujeog.comment.domain.Comment;

public class CommentTestFixture {

	public static class TestComment {
		public static Comment COMMENT1 = Comment.builder()
			.id(1L)
			.content("test-content-1")
			.createdDate(LocalDateTime.now())
			.updatedDate(LocalDateTime.now())
			.paperId(PaperTestFixture.TestPaper.PAPER1.getId())
			.userId(UserTestFixture.TestUser.USER1.getId())
			.build();

		public static Comment COMMENT2 = Comment.builder()
			.id(2L)
			.content("test-content-2")
			.createdDate(LocalDateTime.now())
			.updatedDate(LocalDateTime.now())
			.paperId(PaperTestFixture.TestPaper.PAPER2.getId())
			.userId(UserTestFixture.TestUser.USER1.getId())
			.build();
	}
}

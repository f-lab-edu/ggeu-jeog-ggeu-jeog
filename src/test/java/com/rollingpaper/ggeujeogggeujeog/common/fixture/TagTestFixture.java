package com.rollingpaper.ggeujeogggeujeog.common.fixture;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Tag;

public class TagTestFixture {

	public static class TestTag {
		public static Tag TAG1 = new Tag(
			1L,
			"test_tag_1"
		);

		public static Tag TAG2 = new Tag(
			2L,
			"test_tag_2"
		);

		public static Tag TAG3 = new Tag(
			3L,
			"test_tag_3"
		);
	}
}

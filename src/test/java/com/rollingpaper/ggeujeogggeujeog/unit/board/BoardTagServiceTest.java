package com.rollingpaper.ggeujeogggeujeog.unit.board;

import static com.rollingpaper.ggeujeogggeujeog.common.fixture.BoardTestFixture.TestBoard.*;
import static com.rollingpaper.ggeujeogggeujeog.common.fixture.TagTestFixture.TestTag.*;
import static com.rollingpaper.ggeujeogggeujeog.common.fixture.UserTestFixture.TestUser.*;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rollingpaper.ggeujeogggeujeog.board.application.BoardService;
import com.rollingpaper.ggeujeogggeujeog.board.application.BoardTagService;
import com.rollingpaper.ggeujeogggeujeog.board.domain.Tag;
import com.rollingpaper.ggeujeogggeujeog.board.exception.DuplicatedTagsInBoardException;
import com.rollingpaper.ggeujeogggeujeog.board.exception.NoSuchTagException;
import com.rollingpaper.ggeujeogggeujeog.board.infrastructure.BoardTagMapper;
import com.rollingpaper.ggeujeogggeujeog.board.infrastructure.TagMapper;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardTagRequestDto;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

@ExtendWith(MockitoExtension.class)
public class BoardTagServiceTest {

	@InjectMocks
	private BoardTagService boardTagService;

	@Mock
	private BoardService boardService;

	@Mock
	private BoardTagMapper boardTagMapper;

	@Mock
	private TagMapper tagMapper;

	@Test
	@DisplayName("하나의 보드에 중복된 태그를 달 수 없다.")
	void addBoardTagsWithDuplicatedNames() {
		//given
		List<String> duplicatedTagNames = Arrays.asList(
			TAG1.getName(),
			TAG2.getName()
		);
		List<Tag> duplicatedTag = Arrays.asList(
			TAG1,
			TAG2
		);
		BoardTagRequestDto dto = new BoardTagRequestDto(duplicatedTagNames);
		Long boardId = BOARD1.getId();
		User user = USER1;
		given(tagMapper.findDuplicatedTags(any(), any())).willReturn(duplicatedTag);
		given(boardService.checkBoardOwner(any(), any())).willReturn(BOARD1);

		//when, then
		Assertions.assertThrows(DuplicatedTagsInBoardException.class,
			() -> boardTagService.addBoardTags(dto, boardId, user)
		);

	}

	@ParameterizedTest
	@ValueSource(strings = {"test_tag2", "test_tag1"})
	@DisplayName("중복이 안된 태그 이름들은 태그로 등록이 된다.")
	void addBoardTagsWithNonDuplicatedNames(String tagName) {
		//given
		BoardTagRequestDto dto = new BoardTagRequestDto(
			Arrays.asList(tagName)
		);
		Long boardId = BOARD1.getId();
		User user = USER1;
		given(tagMapper.findDuplicatedTags(any(), any())).willReturn(Collections.emptyList());
		given(boardService.checkBoardOwner(any(), any())).willReturn(BOARD1);

		//when
		boardTagService.addBoardTags(dto, boardId, user);

		//then
		then(tagMapper).should(times(1)).insertTag(any());
		then(boardTagMapper).should(times(1)).insertBoardTag(any(), any());
	}

	@ParameterizedTest
	@ValueSource(strings = {"test_tag_1", "test_tag_2"})
	@DisplayName("등록된 태그를 삭제한다.")
	void removeAssignedBoardTags(String tagName) {
		//given
		BoardTagRequestDto dto = new BoardTagRequestDto(
			Arrays.asList(tagName)
		);
		Long boardId = BOARD1.getId();
		User user = USER1;
		given(boardService.checkBoardOwner(any(), any())).willReturn(BOARD1);
		given(tagMapper.findByBoardIdAndTagName(any(), any())).willReturn(Optional.of(TAG1));

		//when
		boardTagService.removeBoardTags(dto, boardId, user);

		//then
		then(boardTagMapper).should(times(1)).removeBoardTag(any(), any());
	}

	@Test
	@DisplayName("보드에 등록이 안된 태그는 삭제할 수 없다.")
	void removeNotAssignedBoardTag() {
		//given
		BoardTagRequestDto dto = new BoardTagRequestDto(
			Arrays.asList("test_tag_123")
		);
		Long boardId = BOARD1.getId();
		User user = USER1;
		given(boardService.checkBoardOwner(any(), any())).willReturn(BOARD1);
		given(tagMapper.findByBoardIdAndTagName(any(), any())).willThrow(NoSuchTagException.class);

		//when, then
		Assertions.assertThrows(NoSuchTagException.class,
			() -> boardTagService.removeBoardTags(dto, boardId, user)
		);
	}
}

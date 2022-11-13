package com.rollingpaper.ggeujeogggeujeog.integration.board;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rollingpaper.ggeujeogggeujeog.board.application.BoardService;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.BoardController;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardRequestDto;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.UserBoardResponseDto;
import com.rollingpaper.ggeujeogggeujeog.common.config.AbstractContainerBaseTest;
import com.rollingpaper.ggeujeogggeujeog.common.config.TestUserArgumentResolver;
import com.rollingpaper.ggeujeogggeujeog.common.fixture.BoardTestFixture;
import com.rollingpaper.ggeujeogggeujeog.common.fixture.UserTestFixture;

class BoardIntegrationTest extends AbstractContainerBaseTest {

	@Autowired
	private BoardController boardController;

	@Autowired
	private BoardService boardService;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(boardController)
			.setCustomArgumentResolvers(new TestUserArgumentResolver())
			.build();
		objectMapper = new ObjectMapper();
	}

	@Test
	@DisplayName("POST /api/v1/boards - 사용자는 새로운 보드를 작성할 수 있다.")
	void writeBoardByUser() throws Exception {
		//given
		BoardRequestDto boardRequestDto = new BoardRequestDto(
			BoardTestFixture.TestBoard.BOARD1.getBoardTitle(),
			BoardTestFixture.TestBoard.BOARD1.getTheme(),
			BoardTestFixture.TestBoard.BOARD1.isOpened()
		);

		//when, then
		this.mockMvc.perform(post("/api/v1/boards")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(boardRequestDto)))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();
	}

	@Test
	@Transactional
	@DisplayName("PATCH /api/v1/boards/1 - 소유자는 보드를 수정할 수 있다.")
	void updateBoardByOwner() throws Exception {
		//given
		BoardRequestDto boardRequestDto = new BoardRequestDto(
			BoardTestFixture.TestBoard.BOARD2.getBoardTitle(),
			BoardTestFixture.TestBoard.BOARD2.getTheme(),
			BoardTestFixture.TestBoard.BOARD2.isOpened()
		);
		Long boardId = BoardTestFixture.TestBoard.BOARD1.getId();

		//when, then
		this.mockMvc.perform(patch("/api/v1/boards/{boardId}", boardId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(boardRequestDto)))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();
	}

	@Test
	@Transactional
	@DisplayName("DELETE /api/v1/boards/1 - 소유자는 보드를 삭제할 수 있다.")
	void deleteBoardByOwner() throws Exception {
		//given
		Long boardId = BoardTestFixture.TestBoard.BOARD1.getId();

		//when, then
		this.mockMvc.perform(delete("/api/v1/boards/{boardId}", boardId)
				.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();
	}

	@Test
	@DisplayName("소유자의 모든 보드를 조회한다.")
	void getBoardsByOwner() {
		//given
		Long userId = UserTestFixture.TestUser.USER1.getId();

		//when
		List<UserBoardResponseDto> list = boardService.getUserBoard(userId);

		//then
		assertThat(list).hasSize(3);
	}
}

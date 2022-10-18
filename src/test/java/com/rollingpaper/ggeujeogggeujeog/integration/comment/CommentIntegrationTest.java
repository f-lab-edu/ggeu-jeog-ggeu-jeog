package com.rollingpaper.ggeujeogggeujeog.integration.comment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rollingpaper.ggeujeogggeujeog.comment.application.CommentService;
import com.rollingpaper.ggeujeogggeujeog.comment.exception.CommentAlreadyExistException;
import com.rollingpaper.ggeujeogggeujeog.comment.exception.NoSuchCommentException;
import com.rollingpaper.ggeujeogggeujeog.comment.presentation.CommentController;
import com.rollingpaper.ggeujeogggeujeog.comment.presentation.dto.CommentWriteRequestDto;
import com.rollingpaper.ggeujeogggeujeog.common.config.AbstractContainerBaseTest;
import com.rollingpaper.ggeujeogggeujeog.common.config.TestUserArgumentResolver;
import com.rollingpaper.ggeujeogggeujeog.common.fixture.BoardTestFixture;
import com.rollingpaper.ggeujeogggeujeog.common.fixture.PaperTestFixture;
import com.rollingpaper.ggeujeogggeujeog.common.fixture.UserTestFixture;

public class CommentIntegrationTest extends AbstractContainerBaseTest {

	private static final Long PAPER_4_ID = 4L;

	@Autowired
	private CommentController commentController;

	@Autowired
	private CommentService commentService;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(commentController)
			.setCustomArgumentResolvers(new TestUserArgumentResolver())
			.build();
		objectMapper = new ObjectMapper();
	}

	@Test
	@Transactional
	@DisplayName("POST /api/v1/boards/1/rollingpapers/1/comment - 보드 소유자는 댓글을 등록할 수 있다.")
	void writeCommentByBoardOwner() throws Exception {
		//given
		Long boardId = BoardTestFixture.TestBoard.BOARD1.getId();
		Long paperId = PAPER_4_ID;
		CommentWriteRequestDto dto = new CommentWriteRequestDto(
			"This is test"
		);

		//when, then
		this.mockMvc.perform(post(
			"/api/v1/boards/{boardId}/rollingpapers/{paperId}/comment",
			boardId,
			paperId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();
	}

	@Test
	@DisplayName("GET /api/v1/boards/{boardId}/rollingpapers/{paperId}/comment - 사용자는 댓글을 조회할 수 있다.")
	void findCommentByUser() throws Exception {
		//given
		Long boardId = BoardTestFixture.TestBoard.BOARD1.getId();
		Long paperId = PaperTestFixture.TestPaper.PAPER1.getId();

		//when, then
		this.mockMvc.perform(get(
				"/api/v1/boards/{boardId}/rollingpapers/{paperId}/comment",
				boardId,
				paperId))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();

	}

	@Test
	@Transactional
	@DisplayName("PATCH /api/v1/boards/1/rollingpapers/1/comment - 보드 소유자는 댓글을 수정할 수 있다.")
	void updateCommentByBoardOwner() throws Exception {
		//given
		Long boardId = BoardTestFixture.TestBoard.BOARD1.getId();
		Long paperId = PaperTestFixture.TestPaper.PAPER1.getId();
		CommentWriteRequestDto dto = new CommentWriteRequestDto(
			"This is test"
		);

		//when, then
		this.mockMvc.perform(patch(
				"/api/v1/boards/{boardId}/rollingpapers/{paperId}/comment",
				boardId,
				paperId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();
	}

	@Test
	@Transactional
	@DisplayName("DELET /api/v1/boards/{boardId}/rollingpapers/{paperId}/comment - 보드 소유자는 댓글을 삭제할 수 있다.")
	void deleteCommentByBoardOwner() throws Exception {
		//given
		Long boardId = BoardTestFixture.TestBoard.BOARD1.getId();
		Long paperId = PaperTestFixture.TestPaper.PAPER1.getId();

		//when, then
		this.mockMvc.perform(delete(
			"/api/v1/boards/{boardId}/rollingpapers/{paperId}/comment",
				boardId,
				paperId))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();
	}

	@Test
	@DisplayName("댓글은 페이퍼에 한 개만 적을 수 있다.")
	void writeCommentOnlyOne() throws Exception {
		//given
		Long boardId = BoardTestFixture.TestBoard.BOARD1.getId();
		Long paperId = PaperTestFixture.TestPaper.PAPER1.getId();
		CommentWriteRequestDto dto = new CommentWriteRequestDto(
			"This is test"
		);

		//when, then
		Assertions.assertThatThrownBy(() ->
				commentService.writeComment(dto, boardId, paperId, UserTestFixture.TestUser.USER1))
			.isInstanceOf(CommentAlreadyExistException.class)
			.hasFieldOrPropertyWithValue("errorCode", "302")
			.hasFieldOrPropertyWithValue("httpStatus", HttpStatus.BAD_REQUEST)
			.hasMessage("댓글이 이미 존재합니다.");
	}

	@Test
	@DisplayName("댓글이 존재하지 않는다면 삭제할 수 없다.")
	void deleteNotExistComment() {
		//given
		Long boardId = BoardTestFixture.TestBoard.BOARD1.getId();
		Long paperId = PAPER_4_ID;

		//when, then
		Assertions.assertThatThrownBy(() ->
				commentService.deleteComment(boardId, paperId, UserTestFixture.TestUser.USER1))
			.isInstanceOf(NoSuchCommentException.class)
			.hasFieldOrPropertyWithValue("errorCode", "301")
			.hasFieldOrPropertyWithValue("httpStatus", HttpStatus.BAD_REQUEST)
			.hasMessage("존재하지 않는 댓글입니다.");
	}
}

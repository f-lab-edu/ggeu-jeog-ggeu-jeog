package com.rollingpaper.ggeujeogggeujeog.integration.paper;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import com.rollingpaper.ggeujeogggeujeog.common.config.AbstractContainerBaseTest;
import com.rollingpaper.ggeujeogggeujeog.common.config.TestUserArgumentResolver;
import com.rollingpaper.ggeujeogggeujeog.common.fixture.BoardTestFixture;
import com.rollingpaper.ggeujeogggeujeog.common.fixture.ImageTestFixture;
import com.rollingpaper.ggeujeogggeujeog.common.fixture.PaperTestFixture;
import com.rollingpaper.ggeujeogggeujeog.common.fixture.UserTestFixture;
import com.rollingpaper.ggeujeogggeujeog.paper.application.PaperService;
import com.rollingpaper.ggeujeogggeujeog.paper.infrastructure.ImageStorage;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.PaperController;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperResponseDto;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperUpdateRequestDto;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperWriteRequest;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperWriteRequestDto;

public class PaperIntegrationTest extends AbstractContainerBaseTest {

	private static final Long LATEST_PAPER_ID = 5L;

	@Autowired
	private PaperController paperController;

	@Autowired
	private PaperService paperService;

	@Autowired
	private ImageStorage imageStorage;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(paperController)
			.setCustomArgumentResolvers(new TestUserArgumentResolver())
			.build();
		objectMapper = new ObjectMapper();
	}

	@Test
	@DisplayName("보드 소유자는 페이퍼 상세 조회가 가능하다.")
	void getPaperByBoardOwner() {
		//given
		Long paperId = PaperTestFixture.TestPaper.PAPER1.getId();
		String ownerName = PaperTestFixture.TestPaper.PAPER1.getOwnerName();

		//when
		PaperResponseDto responseDto = paperService.getPaper(paperId);

		//then
		assertThat(responseDto.getOwnerName()).isEqualTo(ownerName);
	}

	@Test
	@DisplayName("보드 소유자는 자신의 페이퍼 목록을 조회할 수 있다.")
	void getPapersByBoardOwner() {
		//given
		Long boardId = BoardTestFixture.TestBoard.BOARD1.getId();
		int page = 10;

		//when
		List<PaperResponseDto> papers = paperService.findAllPapers(boardId, page);

		//then
		assertThat(papers).hasSize(2);
	}

	@Test
	@Transactional
	@DisplayName("POST /api/v1/boards/1/rollingpapers - 보드 소유자는 페이퍼를 작성할 수 있다.")
	void writePaperByBoardOwner() throws Exception {
		//given
		MockMultipartFile mockMultipartFileImage = new MockMultipartFile(
			"image",
			"image",
			MediaType.IMAGE_JPEG_VALUE,
			new byte[10]
		);
		PaperWriteRequestDto paperWriteRequestDto = new PaperWriteRequestDto(
			PaperTestFixture.TestPaper.PAPER1.getOwnerName(),
			PaperTestFixture.TestPaper.PAPER1.getContent(),
			PaperTestFixture.TestPaper.PAPER1.getContentMeta()
		);
		MockMultipartFile mockMultipartFileDto = new MockMultipartFile(
			"request",
			"request",
			"application/json",
			objectMapper.writeValueAsBytes(paperWriteRequestDto)
		);
		Long boardId = PaperTestFixture.TestPaper.PAPER1.getBoardId();

		//when, then
		this.mockMvc.perform(multipart("/api/v1/boards/{boardId}/rollingpapers", boardId)
				.file(mockMultipartFileImage)
				.file(mockMultipartFileDto))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();
	}

	@Test
	@Transactional
	@DisplayName("POST /api/v1/boards/1/rollingpapers/1 - 보드 소유자는 페이퍼 수정이 가능하다.")
	void updatePaperByOwner() throws Exception {
		//given
		MockMultipartFile mockMultipartFileImage = new MockMultipartFile(
			"image",
			"image",
			MediaType.IMAGE_JPEG_VALUE,
			new byte[10]
		);
		PaperUpdateRequestDto paperUpdateRequestDto = new PaperUpdateRequestDto(
			PaperTestFixture.TestPaper.PAPER2.getOwnerName(),
			PaperTestFixture.TestPaper.PAPER2.getContent(),
			PaperTestFixture.TestPaper.PAPER2.getContentMeta()
		);
		MockMultipartFile mockMultipartFileDto = new MockMultipartFile(
			"request",
			"request",
			"application/json",
			objectMapper.writeValueAsBytes(paperUpdateRequestDto)
		);
		Long boardId = PaperTestFixture.TestPaper.PAPER1.getBoardId();
		Long paperId = PaperTestFixture.TestPaper.PAPER1.getId();

		//when, then
		this.mockMvc.perform(multipart(
				"/api/v1/boards/{boardId}/rollingpapers/{paperId}",
				boardId,
				paperId)
				.file(mockMultipartFileImage)
				.file(mockMultipartFileDto))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();
	}

	@Test
	@Transactional
	@DisplayName("DELETE /api/v1/boards/1/rollingpapers/1 - 보드 소유자는 페이퍼를 삭제할 수 있다.")
	void deletePaperByOwner() throws Exception {
		//given
		Long boardId = PaperTestFixture.TestPaper.PAPER1.getBoardId();
		Long paperId = PaperTestFixture.TestPaper.PAPER1.getId();

		//when, then
		this.mockMvc.perform(delete(
				"/api/v1/boards/{boardId}/rollingpapers/{paperId}",
				boardId,
				paperId))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();
	}

	@Test
	@Transactional
	@DisplayName("페이퍼와 등록된 이미지는 외부 저장소에 저장한다.")
	void savePaperImageInObjectStorage() {
		//given
		PaperWriteRequest dto = PaperWriteRequest.builder()
			.ownerName(PaperTestFixture.TestPaper.PAPER1.getOwnerName())
			.content(PaperTestFixture.TestPaper.PAPER1.getContent())
			.contentMeta(PaperTestFixture.TestPaper.PAPER1.getContentMeta())
			.imageFile(ImageTestFixture.MOCK_JPEG_FILE)
			.userId(UserTestFixture.TestUser.USER3.getId())
			.boardId(PaperTestFixture.TestPaper.PAPER1.getBoardId())
			.build();


		//when
		paperService.writePaper(dto);
		PaperResponseDto paperResponseDto = paperService.getPaper(LATEST_PAPER_ID);
		String imageUrl = paperResponseDto.getImageUrl();

		//then
		Assertions.assertTrue(imageStorage.doesImageFileExist(imageUrl));
	}
}

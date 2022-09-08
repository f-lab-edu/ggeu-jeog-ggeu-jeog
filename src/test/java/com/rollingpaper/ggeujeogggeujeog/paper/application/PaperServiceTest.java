package com.rollingpaper.ggeujeogggeujeog.paper.application;

import static com.rollingpaper.ggeujeogggeujeog.common.fixture.BoardTestFixture.*;
import static com.rollingpaper.ggeujeogggeujeog.common.fixture.PaperTestFixture.*;
import static com.rollingpaper.ggeujeogggeujeog.common.fixture.UserTestFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rollingpaper.ggeujeogggeujeog.common.fixture.ImageTestFixture;
import com.rollingpaper.ggeujeogggeujeog.paper.domain.Paper;
import com.rollingpaper.ggeujeogggeujeog.paper.exception.NoSuchPaperException;
import com.rollingpaper.ggeujeogggeujeog.paper.infrastructure.ImageStorage;
import com.rollingpaper.ggeujeogggeujeog.paper.infrastructure.PaperMapper;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperResponseDto;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperUpdateRequestDto;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperWriteRequest;

@ExtendWith(MockitoExtension.class)
class PaperServiceTest {

	@InjectMocks
	private PaperServiceImpl paperServiceImpl;

	@Mock
	private PaperMapper paperMapper;

	@Mock
	private ImageStorage imageStorage;

	@Test
	@DisplayName("이미지 파일이 있고 페이퍼 등록에 성공한다.")
	void registerPaper() {
		//given
		PaperWriteRequest paperWriteRequest = PaperWriteRequest.builder()
			.ownerName(TestPaper.PAPER1.getOwnerName())
			.content(TestPaper.PAPER1.getContent())
			.contentMeta(TestPaper.PAPER1.getContentMeta())
			.imageFile(ImageTestFixture.MOCK_JPEG_FILE)
			.boardId(TestPaper.PAPER1.getBoardId())
			.userId(TestPaper.PAPER1.getUserId())
			.build();

		//when
		paperServiceImpl.writePaper(paperWriteRequest);

		//then
		then(imageStorage).should(times(1)).store(any());
		then(paperMapper).should(times(1)).save(any());
	}

	@Test
	@DisplayName("페이퍼 리스트를 보드 id를 통해 조회한다.")
	void findAllPaper() {
		//when
		paperServiceImpl.findAllPapers(TestBoard.BOARD1.getId(), 10);

		//then
		then(paperMapper).should(times(1)).findAll(anyLong(), anyInt());
	}

	@Test
	@DisplayName("페이퍼 id를 통해 조회한다.")
	void findPaper() {
		//given
		given(paperMapper.findById(any())).willReturn(Optional.of(TestPaper.PAPER1));

		//when
		paperServiceImpl.getPaper(TestPaper.PAPER1.getId());

		//then
		then(paperMapper).should(times(1)).findById(any());
	}

	@Test
	@DisplayName("페이퍼 조회에 실패한다.")
	void findPaperWithNoSuchPaper() {
		//given
		given(paperMapper.findById(any())).willReturn(Optional.ofNullable(null));

		//then
		assertThrows(NoSuchPaperException.class, () -> paperServiceImpl.getPaper(any()));
	}

	@Test
	@DisplayName("페이퍼를 리스트를 유저 정보로 조회한다.")
	void findAllByUserId() {
		//given
		List<Paper> papers = Arrays.asList(TestPaper.PAPER1, TestPaper.PAPER2);
		when(paperMapper.findAllByUserId(anyLong(), anyInt())).thenReturn(papers);

		//when
		List<PaperResponseDto> papersByUser = paperServiceImpl.findAllByUserId(TestUser.USER1.getId(), 10);

		//then
		assertThat(papersByUser).usingRecursiveComparison().isEqualTo(papers);
	}

	@Test
	@DisplayName("페이퍼를 이미지 없이 등록할 경우, 페이퍼의 이미지 경로는 공백으로 저장된다.")
	void registerPaperWithoutImageFile() {
		//given
		PaperWriteRequest paperWriteRequest = PaperWriteRequest.builder()
			.ownerName(TestPaper.PAPER1.getOwnerName())
			.content(TestPaper.PAPER1.getContent())
			.contentMeta(TestPaper.PAPER1.getContentMeta())
			.imageFile(null)
			.boardId(TestPaper.PAPER1.getBoardId())
			.userId(TestPaper.PAPER1.getUserId())
			.build();
		given(imageStorage.store(null)).willReturn("");

		//when
		paperServiceImpl.writePaper(paperWriteRequest);

		//then
		then(paperMapper).should(times(1)).save(any());
	}

	@Test
	@DisplayName("등록된 페이퍼를 id로 삭제한다.")
	void deletePaperWithPaperId() {
		//when
		paperServiceImpl.delete(TestPaper.PAPER1.getId());

		//then
		then(paperMapper).should(times(1)).delete(anyLong());
	}

	@Test
	@DisplayName("등록된 페이퍼를 새로운 이미지와 함께 수정한다.")
	void updatePaperWithNewImage() {
		//given
		PaperUpdateRequestDto paperUpdateRequestDto = new PaperUpdateRequestDto(
			TestPaper.PAPER2.getOwnerName(),
			TestPaper.PAPER2.getContent(),
			TestPaper.PAPER2.getContentMeta()
		);
		given(imageStorage.store(ImageTestFixture.MOCK_JPEG_FILE)).willReturn("/" + ImageTestFixture.IMAGE_NAME1);
		given(paperMapper.findById(anyLong())).willReturn(Optional.of(TestPaper.PAPER1));

		//when
		paperServiceImpl.update(paperUpdateRequestDto, ImageTestFixture.MOCK_JPEG_FILE, TestPaper.PAPER1.getId());

		//then
		then(paperMapper).should(times(1)).update(any());
		then(imageStorage).should(times(1)).store(any());
	}

	@Test
	@DisplayName("등록된 페이퍼를 새로운 이미지 없이 수정한다.")
	void updatePaperWithoutNewImage() {
		//given
		PaperUpdateRequestDto paperUpdateRequestDto = new PaperUpdateRequestDto(
			TestPaper.PAPER2.getOwnerName(),
			TestPaper.PAPER2.getContent(),
			TestPaper.PAPER2.getContentMeta()
		);
		given(imageStorage.store(null)).willReturn("");
		given(paperMapper.findById(anyLong())).willReturn(Optional.of(TestPaper.PAPER1));

		//when
		paperServiceImpl.update(paperUpdateRequestDto, null, TestPaper.PAPER1.getId());

		//then
		then(paperMapper).should(times(1)).update(any());
		then(imageStorage).should(times(1)).store(any());
	}
}
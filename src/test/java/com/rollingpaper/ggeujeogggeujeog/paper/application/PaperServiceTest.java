package com.rollingpaper.ggeujeogggeujeog.paper.application;

import static com.rollingpaper.ggeujeogggeujeog.common.fixture.BoardTestFixture.*;
import static com.rollingpaper.ggeujeogggeujeog.common.fixture.PaperTestFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rollingpaper.ggeujeogggeujeog.common.fixture.ImageTestFixture;
import com.rollingpaper.ggeujeogggeujeog.common.util.ImageStorage;
import com.rollingpaper.ggeujeogggeujeog.paper.exception.NoSuchPaperException;
import com.rollingpaper.ggeujeogggeujeog.paper.infrastructure.PaperMapper;
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
	@DisplayName("페이퍼 등록에 성공한다.")
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
	@DisplayName("페이퍼 리스트를 조회한다.")
	void findAllPaper() {
		//when
		paperServiceImpl.findAllPaper(TestBoard.BOARD1.getId(), 10);

		//then
		then(paperMapper).should(times(1)).findAll(anyLong(), anyInt());
	}

	@Test
	@DisplayName("페이퍼를 조회한다.")
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
}
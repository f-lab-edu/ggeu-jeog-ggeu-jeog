package com.rollingpaper.ggeujeogggeujeog.paper.application;

import static com.rollingpaper.ggeujeogggeujeog.common.fixture.BoardTestFixture.*;
import static com.rollingpaper.ggeujeogggeujeog.common.fixture.PaperTestFixture.*;
import static com.rollingpaper.ggeujeogggeujeog.common.fixture.UserTestFixture.*;
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
		//when
		paperServiceImpl.findAllByUserId(TestUser.USER1.getId(), 10);

		//then
		then(paperMapper).should(times(1)).findAllByUserId(anyLong(), anyInt());
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
}
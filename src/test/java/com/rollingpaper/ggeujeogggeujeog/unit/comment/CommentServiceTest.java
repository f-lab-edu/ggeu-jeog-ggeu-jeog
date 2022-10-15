package com.rollingpaper.ggeujeogggeujeog.unit.comment;

import static com.rollingpaper.ggeujeogggeujeog.common.fixture.BoardTestFixture.*;
import static com.rollingpaper.ggeujeogggeujeog.common.fixture.CommentTestFixture.*;
import static com.rollingpaper.ggeujeogggeujeog.common.fixture.PaperTestFixture.*;
import static com.rollingpaper.ggeujeogggeujeog.common.fixture.UserTestFixture.*;
import static org.assertj.core.api.Assertions.*;
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

import com.rollingpaper.ggeujeogggeujeog.board.application.BoardService;
import com.rollingpaper.ggeujeogggeujeog.comment.application.CommentService;
import com.rollingpaper.ggeujeogggeujeog.comment.domain.Comment;
import com.rollingpaper.ggeujeogggeujeog.comment.infrastructure.CommentMapper;
import com.rollingpaper.ggeujeogggeujeog.comment.presentation.dto.CommentResponseDto;
import com.rollingpaper.ggeujeogggeujeog.comment.presentation.dto.CommentWriteRequestDto;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

	@InjectMocks
	private CommentService commentService;

	@Mock
	private CommentMapper commentMapper;

	@Mock
	private BoardService boardService;

	@Test
	@DisplayName("페이퍼에 댓글 등록을 성공한다.")
	void addComment() {
		//given
		CommentWriteRequestDto commentWriteRequestDto =
			new CommentWriteRequestDto(TestComment.COMMENT1.getContent());

		//when
		commentService.writeComment(
			commentWriteRequestDto,
			TestBoard.BOARD1.getId(),
			TestPaper.PAPER1.getId(),
			TestUser.USER1
		);

		//then
		then(commentMapper).should(times(1)).save(any());
	}

	@Test
	@DisplayName("페이퍼 id를 통해 댓글을 조회한다.")
	void findCommentByPaperId() {
		//given
		given(commentMapper.findByPaperId(any())).willReturn(Optional.of(TestComment.COMMENT1));

		//when
		CommentResponseDto response = commentService.getCommentByPaperId(TestPaper.PAPER1.getId());

		//then
		assertThat(TestComment.COMMENT1.getContent()).isEqualTo(response.getContent());
	}

	@Test
	@DisplayName("사용자가 작성한 댓글을 모두 조회한다.")
	void findCommentsByUserId() {
		//given
		List<Comment> comments = Arrays.asList(TestComment.COMMENT1, TestComment.COMMENT2);
		when(commentMapper.findAllByUserId(anyLong(), anyInt())).thenReturn(comments);

		//when
		List<CommentResponseDto> commentsByUser = commentService.getCommentsByUser(TestUser.USER1.getId(), 10);

		//then
		assertThat(commentsByUser).usingRecursiveComparison().isEqualTo(comments);
	}

	@Test
	@DisplayName("보드 소유자가 댓글을 삭제할 수 있다.")
	void deleteComment() {
		//given
		given(boardService.checkBoardOwner(anyLong(), any())).willReturn(TestBoard.BOARD1);

		//when
		commentService.deleteComment(TestBoard.BOARD1.getId(), TestPaper.PAPER1.getId(), TestUser.USER1);

		//then
		then(commentMapper).should(times(1)).delete(anyLong());
	}

	@Test
	@DisplayName("보드 소유자가 댓글을 수정할 수 있다.")
	void updateComment() {
		//given
		CommentWriteRequestDto dto = new CommentWriteRequestDto("test-content");
		given(boardService.checkBoardOwner(anyLong(), any())).willReturn(TestBoard.BOARD1);

		//when
		commentService.updateComment(dto, TestBoard.BOARD1.getId(), TestPaper.PAPER1.getId(), TestUser.USER1);

		//then
		then(commentMapper).should(times(1)).update(any());
	}
}
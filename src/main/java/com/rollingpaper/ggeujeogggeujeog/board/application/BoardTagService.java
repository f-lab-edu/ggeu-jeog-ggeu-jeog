package com.rollingpaper.ggeujeogggeujeog.board.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rollingpaper.ggeujeogggeujeog.board.domain.BoardTagRepository;
import com.rollingpaper.ggeujeogggeujeog.board.domain.Tag;
import com.rollingpaper.ggeujeogggeujeog.board.domain.TagRepository;
import com.rollingpaper.ggeujeogggeujeog.board.exception.DuplicatedTagsInBoardException;
import com.rollingpaper.ggeujeogggeujeog.board.exception.NoSuchTagException;
import com.rollingpaper.ggeujeogggeujeog.board.presentation.dto.BoardTagRequestDto;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardTagService {

	private final BoardService boardService;
	private final BoardTagRepository boardTagRepository;
	private final TagRepository tagRepository;

	@Transactional
	public void addBoardTags(BoardTagRequestDto dto, Long boardId, User currentUser) {
		boardService.checkBoardOwner(boardId, currentUser);
		dto.getTagNames()
			.forEach(tagName -> {
				checkDuplicatedTagsInBoard(boardId, tagName);
				Tag newTag = Tag.createTagUsingName(tagName);
				tagRepository.insertTag(newTag);
				boardTagRepository.insertBoardTag(boardId, newTag.getId());
			});
	}

	private void checkDuplicatedTagsInBoard(Long boardId, String tagName) {
		if(!tagRepository.findDuplicatedTags(boardId, tagName).isEmpty())
			throw new DuplicatedTagsInBoardException();
	}

	@Transactional
	public void removeBoardTags(BoardTagRequestDto dto, Long boardId, User currentUser) {
		boardService.checkBoardOwner(boardId, currentUser);
		dto.getTagNames()
			.forEach(tagName -> {
				Tag tag = tagRepository.findByBoardIdAndTagName(boardId, tagName)
					.orElseThrow(NoSuchTagException::new);
				boardTagRepository.removeBoardTag(boardId, tag.getId());
			});
	}
}

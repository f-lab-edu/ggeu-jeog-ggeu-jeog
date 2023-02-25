package com.rollingpaper.ggeujeogggeujeog.board.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Tag;
import com.rollingpaper.ggeujeogggeujeog.board.domain.TagRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class TagRepositoryAdaptor implements TagRepository {

	private final TagMapper tagMapper;

	@Override
	public Optional<Tag> findByBoardIdAndTagName(Long boardId, String tagName) {
		return tagMapper.findByBoardIdAndTagName(boardId, tagName);
	}

	@Override
	public List<Tag> findDuplicatedTags(Long boardId, String tagName) {
		return tagMapper.findDuplicatedTags(boardId, tagName);
	}

	@Override
	public void insertTag(Tag tag) {
		tagMapper.insertTag(tag);
	}
}

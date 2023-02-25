package com.rollingpaper.ggeujeogggeujeog.board.domain;

import java.util.List;
import java.util.Optional;

public interface TagRepository {

	Optional<Tag> findByBoardIdAndTagName(Long boardId, String tagName);

	List<Tag> findDuplicatedTags(Long boardId, String tagName);

	void insertTag(Tag tag);
}

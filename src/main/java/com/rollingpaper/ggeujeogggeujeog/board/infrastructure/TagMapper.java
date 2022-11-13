package com.rollingpaper.ggeujeogggeujeog.board.infrastructure;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.rollingpaper.ggeujeogggeujeog.board.domain.Tag;

@Repository
@Mapper
public interface TagMapper {

	Optional<Tag> findByBoardIdAndTagName(Long boardId, String tagName);

	List<Tag> findDuplicatedTags(Long boardId, String tagName);

	void insertTag(Tag tag);
}

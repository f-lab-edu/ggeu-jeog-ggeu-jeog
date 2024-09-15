package com.rollingpaper.ggeujeogggeujeog.paper.infrastructure;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.rollingpaper.ggeujeogggeujeog.paper.domain.Paper;

@Mapper
public interface PaperMapper {

	void save(Paper paper);

	List<Paper> findAll(Long boardId, int page);

	Optional<Paper> findById(Long paperId);

	List<Paper> findAllByUserId(Long userId, int page);

	void update(Paper paper);

	void delete(Long paperId);
}

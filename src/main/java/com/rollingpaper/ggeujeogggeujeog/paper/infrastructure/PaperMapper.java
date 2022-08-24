package com.rollingpaper.ggeujeogggeujeog.paper.infrastructure;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.rollingpaper.ggeujeogggeujeog.paper.domain.Paper;

@Repository
@Mapper
public interface PaperMapper {

	void save(Paper paper);
	List<Paper> findAll(Long boardId, int page);
	Optional<Paper> findById(Long paperId);
	List<Paper> findAllByUserId(Long userId, int page);
}

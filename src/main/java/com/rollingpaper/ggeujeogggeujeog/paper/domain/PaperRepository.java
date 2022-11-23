package com.rollingpaper.ggeujeogggeujeog.paper.domain;

import java.util.List;
import java.util.Optional;

public interface PaperRepository {

	void save(Paper paper);

	List<Paper> findAll(Long boardId, int page);

	Optional<Paper> findById(Long paperId);

	List<Paper> findAllByUserId(Long userId, int page);

	void update(Paper paper);

	void delete(Long paperId);
}

package com.rollingpaper.ggeujeogggeujeog.paper.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.rollingpaper.ggeujeogggeujeog.paper.domain.Paper;
import com.rollingpaper.ggeujeogggeujeog.paper.domain.PaperRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class PaperRepositoryAdaptor implements PaperRepository {

	private final PaperMapper paperMapper;

	@Override
	public void save(Paper paper) {
		paperMapper.save(paper);
	}

	@Override
	public List<Paper> findAll(Long boardId, int page) {
		return paperMapper.findAll(boardId, page);
	}

	@Override
	public Optional<Paper> findById(Long paperId) {
		return paperMapper.findById(paperId);
	}

	@Override
	public List<Paper> findAllByUserId(Long userId, int page) {
		return paperMapper.findAllByUserId(userId, page);
	}

	@Override
	public void update(Paper paper) {
		paperMapper.update(paper);
	}

	@Override
	public void delete(Long paperId) {
		paperMapper.delete(paperId);
	}
}

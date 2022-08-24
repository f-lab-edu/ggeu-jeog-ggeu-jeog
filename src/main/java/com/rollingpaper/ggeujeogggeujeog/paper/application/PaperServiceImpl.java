package com.rollingpaper.ggeujeogggeujeog.paper.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rollingpaper.ggeujeogggeujeog.common.util.ImageStorage;
import com.rollingpaper.ggeujeogggeujeog.paper.exception.NoSuchPaperException;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperResponseDto;
import com.rollingpaper.ggeujeogggeujeog.paper.domain.Paper;
import com.rollingpaper.ggeujeogggeujeog.paper.infrastructure.PaperMapper;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperWriteRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaperServiceImpl implements PaperService {

	private final PaperMapper paperMapper;
	private final ImageStorage imageStorage;

	@Override
	@Transactional
	public void writePaper(PaperWriteRequest dto) {
		String imageUrl = imageStorage.store(dto.getImageFile());
		paperMapper.save(PaperWriteRequest.toEntity(dto, imageUrl));
	}

	@Override
	@Transactional(readOnly = true)
	public List<PaperResponseDto> findAllPaper(Long boardId, int page) {
		return paperMapper.findAll(boardId, page)
			.stream()
			.map(paper -> new PaperResponseDto(paper.getOwnerName(), paper.getImageUrl(),
				paper.getContent(), paper.getContentMeta(),
				paper.getCreatedDate(), paper.getUpdatedDate()))
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public PaperResponseDto getPaper(Long paperId) {
		Paper paper = paperMapper.findById(paperId)
			.orElseThrow(NoSuchPaperException::new);
		return PaperResponseDto.from(paper);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PaperResponseDto> findAllByUserId(Long userId, int page) {
		return paperMapper.findAllByUserId(userId, page)
			.stream()
			.map(paper -> new PaperResponseDto(paper.getOwnerName(), paper.getImageUrl(),
				paper.getContent(), paper.getContentMeta(),
				paper.getCreatedDate(), paper.getUpdatedDate()))
			.collect(Collectors.toList());
	}
}

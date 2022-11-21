package com.rollingpaper.ggeujeogggeujeog.paper.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rollingpaper.ggeujeogggeujeog.paper.domain.Paper;
import com.rollingpaper.ggeujeogggeujeog.paper.domain.PaperRepository;
import com.rollingpaper.ggeujeogggeujeog.paper.exception.NoSuchPaperException;
import com.rollingpaper.ggeujeogggeujeog.paper.infrastructure.ImageStorage;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperResponseDto;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperUpdateRequestDto;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperWriteRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaperServiceImpl implements PaperService {

	private final PaperRepository paperRepository;
	private final ImageStorage imageStorage;

	@Override
	@Transactional
	public void writePaper(PaperWriteRequest dto) {
		String imageUrl = imageStorage.store(dto.getImageFile());
		paperRepository.save(PaperWriteRequest.toEntity(dto, imageUrl));
	}

	@Override
	@Transactional(readOnly = true)
	public List<PaperResponseDto> findAllPapers(Long boardId, int page) {
		return paperRepository.findAll(boardId, page)
			.stream()
			.map(paper -> new PaperResponseDto(paper.getOwnerName(), paper.getImageUrl(),
				paper.getContent(), paper.getContentMeta(),
				paper.getCreatedDate(), paper.getUpdatedDate()))
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public PaperResponseDto getPaper(Long paperId) {
		Paper paper = paperRepository.findById(paperId)
			.orElseThrow(NoSuchPaperException::new);
		return PaperResponseDto.from(paper);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PaperResponseDto> findAllByUserId(Long userId, int page) {
		return paperRepository.findAllByUserId(userId, page)
			.stream()
			.map(paper -> new PaperResponseDto(paper.getOwnerName(), paper.getImageUrl(),
				paper.getContent(), paper.getContentMeta(),
				paper.getCreatedDate(), paper.getUpdatedDate()))
			.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void update(PaperUpdateRequestDto dto, MultipartFile imageFile, Long paperId) {
		String imageUrl = imageStorage.store(imageFile);
		Paper paper = paperRepository.findById(paperId)
			.orElseThrow(NoSuchPaperException::new);
		paper.updatePaper(dto, imageUrl);
		paperRepository.update(paper);
	}

	@Override
	@Transactional
	public void delete(Long paperId) {
		paperRepository.delete(paperId);
	}
}

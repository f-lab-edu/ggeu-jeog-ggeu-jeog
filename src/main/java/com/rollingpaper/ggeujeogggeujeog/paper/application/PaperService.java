package com.rollingpaper.ggeujeogggeujeog.paper.application;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperResponseDto;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperUpdateRequestDto;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperWriteRequest;

public interface PaperService {

	void writePaper(PaperWriteRequest dto);
	List<PaperResponseDto> findAllPapers(Long boardId, int page);
	PaperResponseDto getPaper(Long paperId);
	List<PaperResponseDto> findAllByUserId(Long boardId, int page);
	void update(PaperUpdateRequestDto dto, MultipartFile imageFile, Long paperId);
	void delete(Long paperId);
}

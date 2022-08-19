package com.rollingpaper.ggeujeogggeujeog.paper.application;

import java.util.List;

import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperResponseDto;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperWriteRequest;

public interface PaperService {

	void writePaper(PaperWriteRequest dto);
	List<PaperResponseDto> findAllPaper(Long boardId, int page);
	PaperResponseDto getPaper(Long paperId);
}

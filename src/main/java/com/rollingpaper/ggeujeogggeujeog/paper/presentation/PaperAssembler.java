package com.rollingpaper.ggeujeogggeujeog.paper.presentation;

import org.springframework.web.multipart.MultipartFile;

import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperWriteRequest;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperWriteRequestDto;

public class PaperAssembler {

	public static PaperWriteRequest assembleWriteDto(
		PaperWriteRequestDto dto, MultipartFile imageFile, Long boardId, Long userId
	) {
		return PaperWriteRequest.builder()
			.ownerName(dto.getOwnerName())
			.content(dto.getContent())
			.contentMeta(dto.getContentMeta())
			.imageFile(imageFile)
			.boardId(boardId)
			.userId(userId)
			.build();
	}
}

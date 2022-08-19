package com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto;

import org.springframework.web.multipart.MultipartFile;

import com.rollingpaper.ggeujeogggeujeog.paper.domain.Paper;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PaperWriteRequest {

	private String ownerName;
	private String content;
	private String contentMeta;
	private MultipartFile imageFile;
	private Long userId;
	private Long boardId;

	@Builder
	public PaperWriteRequest(String ownerName, String content, String contentMeta,
		MultipartFile imageFile, Long userId, Long boardId) {
		this.ownerName = ownerName;
		this.content = content;
		this.contentMeta = contentMeta;
		this.imageFile = imageFile;
		this.userId = userId;
		this.boardId = boardId;
	}

	public static Paper toEntity(PaperWriteRequest dto, String imageUrl) {
		return Paper.builder()
			.ownerName(dto.getOwnerName())
			.content(dto.getContent())
			.contentMeta(dto.getContentMeta())
			.imageUrl(imageUrl)
			.boardId(dto.getBoardId())
			.userId(dto.getUserId())
			.build();
	}
}
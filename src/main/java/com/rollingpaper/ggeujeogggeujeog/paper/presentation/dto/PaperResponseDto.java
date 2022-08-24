package com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto;

import java.time.LocalDateTime;

import com.rollingpaper.ggeujeogggeujeog.paper.domain.Paper;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PaperResponseDto {

	private String ownerName;
	private String imageUrl;
	private String content;
	private String contentMeta;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

	@Builder
	public PaperResponseDto(String ownerName, String imageUrl, String content, String contentMeta,
		LocalDateTime createdDate, LocalDateTime updatedDate) {
		this.ownerName = ownerName;
		this.imageUrl = imageUrl;
		this.content = content;
		this.contentMeta = contentMeta;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public static PaperResponseDto from(Paper paper) {
		return PaperResponseDto.builder()
			.ownerName(paper.getOwnerName())
			.imageUrl(paper.getImageUrl())
			.content(paper.getContent())
			.contentMeta(paper.getContentMeta())
			.createdDate(paper.getCreatedDate())
			.updatedDate(paper.getUpdatedDate())
			.build();
	}
}

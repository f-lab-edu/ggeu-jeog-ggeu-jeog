package com.rollingpaper.ggeujeogggeujeog.paper.domain;

import java.time.LocalDateTime;

import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperUpdateRequestDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Paper {
	private Long id;
	private String ownerName;
	private String content;
	private String contentMeta;
	private String imageUrl;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private Long userId;
	private Long boardId;

	@Builder
	public Paper(Long id, String ownerName, String content, String contentMeta, String imageUrl,
		LocalDateTime createdDate, LocalDateTime updatedDate, Long userId, Long boardId) {
		this.id = id;
		this.ownerName = ownerName;
		this.content = content;
		this.contentMeta = contentMeta;
		this.imageUrl = imageUrl;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.userId = userId;
		this.boardId = boardId;
	}

	private void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	private void setContent(String content) {
		this.content = content;
	}

	private void setContentMeta(String contentMeta) {
		this.contentMeta = contentMeta;
	}

	private void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void updatePaper(PaperUpdateRequestDto dto, String imageUrl) {
		setOwnerName(dto.getOwnerName());
		setContent(dto.getContent());
		setContentMeta(dto.getContentMeta());
		setImageUrl(imageUrl);
	}
}

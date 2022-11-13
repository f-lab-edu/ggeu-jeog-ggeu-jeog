package com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaperWriteRequestDto {

	@NotNull
	private String ownerName;

	@NotBlank
	private String content;

	@NotNull
	private String contentMeta;

	public PaperWriteRequestDto(String ownerName, String content, String contentMeta) {
		this.ownerName = ownerName;
		this.content = content;
		this.contentMeta = contentMeta;
	}
}

package com.rollingpaper.ggeujeogggeujeog.paper.presentation;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rollingpaper.ggeujeogggeujeog.paper.application.PaperService;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperResponseDto;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperWriteRequestDto;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/users")
@RequiredArgsConstructor
@RestController
public class PaperController {

	private final PaperService paperService;

	@PostMapping("/{userId}/boards/{boardId}/rollingpapers")
	public ResponseEntity<Void> writePaper(
		@RequestPart(value = "request") @Valid PaperWriteRequestDto dto,
		@RequestPart(value = "image") MultipartFile imageFile,
		@PathVariable Long boardId,
		@PathVariable Long userId
	) {
		paperService.writePaper(
			PaperAssembler.assembleWriteDto(dto, imageFile, boardId, userId)
		);
		return ResponseEntity.status(OK).build();
	}

	@GetMapping("/{userId}/boards/{boardId}/rollingpapers/all")
	public ResponseEntity<List<PaperResponseDto>> findAllPapers(
		@PathVariable Long boardId,
		@RequestParam(value = "page", defaultValue = "10") int page
	) {
		List<PaperResponseDto> paperResponseDtos = paperService.findAllPapers(boardId, page);
		return ResponseEntity.ok(paperResponseDtos);
	}

	@GetMapping("/{userId}/boards/{boardId}/rollingpapers/{paperId}")
	public ResponseEntity<PaperResponseDto> getPaper(
		@PathVariable Long paperId
	) {
		PaperResponseDto paperResponseDto = paperService.getPaper(paperId);
		return ResponseEntity.ok(paperResponseDto);
	}
}

package com.rollingpaper.ggeujeogggeujeog.paper.presentation;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.CurrentUser;
import com.rollingpaper.ggeujeogggeujeog.paper.application.PaperService;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperResponseDto;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperUpdateRequestDto;
import com.rollingpaper.ggeujeogggeujeog.paper.presentation.dto.PaperWriteRequestDto;
import com.rollingpaper.ggeujeogggeujeog.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class PaperController {

	private final PaperService paperService;

	@PostMapping("/boards/{boardId}/rollingpapers")
	public ResponseEntity<Void> writePaper(
		@RequestPart(value = "request") @Valid PaperWriteRequestDto dto,
		@RequestPart(value = "image") MultipartFile imageFile,
		@PathVariable Long boardId,
		@CurrentUser User user
	) {
		paperService.writePaper(
			PaperAssembler.assembleWriteDto(dto, imageFile, boardId, user.getId())
		);
		return ResponseEntity.status(OK).build();
	}

	@GetMapping("/boards/{boardId}/rollingpapers")
	public ResponseEntity<List<PaperResponseDto>> findAllPapers(
		@PathVariable Long boardId,
		@RequestParam(value = "page", defaultValue = "10") int page
	) {
		List<PaperResponseDto> paperResponseDtos = paperService.findAllPapers(boardId, page);
		return ResponseEntity.ok(paperResponseDtos);
	}

	@GetMapping("/boards/{boardId}/rollingpapers/{paperId}")
	public ResponseEntity<PaperResponseDto> getPaper(
		@PathVariable Long paperId
	) {
		PaperResponseDto paperResponseDto = paperService.getPaper(paperId);
		return ResponseEntity.ok(paperResponseDto);
	}

	@PostMapping("/boards/{boardId}/rollingpapers/{paperId}")
	public ResponseEntity<Void> update(
		@RequestPart(value = "request") @Valid PaperUpdateRequestDto dto,
		@RequestPart(value = "image") MultipartFile imageFile,
		@PathVariable Long paperId
	) {
		paperService.update(dto, imageFile, paperId);
		return ResponseEntity.status(OK).build();
	}

	@DeleteMapping("/boards/{boardId}/rollingpapers/{paperId}")
	public ResponseEntity<Void> delete(
		@PathVariable Long paperId
	) {
		paperService.delete(paperId);
		return ResponseEntity.status(OK).build();
	}
}

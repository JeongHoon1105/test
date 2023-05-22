package com.tapanda.tapanda.controller.api.basic;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tapanda.tapanda.exception.ImageNotFoundException;
import com.tapanda.tapanda.responseDto.FileResponseDto;
import com.tapanda.tapanda.service.FileStorageService;
import com.tapanda.tapanda.service.ImageService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@Api(value = "FILE Rest API")
@RequiredArgsConstructor
public class FileController {

	private final FileStorageService fileStorageService;
	
	private final ImageService imageService;

	@GetMapping("/tapanda/user/{iid}")
	public ResponseEntity<byte[]> downloadUserFile(@PathVariable String iid, HttpServletResponse response) {
		if (!imageService.isExistByIid(iid)) {
			throw new ImageNotFoundException("사진을 찾을수 없습니다.", null, null);
		}
		FileResponseDto dto = new FileResponseDto(
				imageService.findByIid(iid).orElseThrow(() -> new ImageNotFoundException("사진을 찾을수 없습니다.", null, null)));

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fileStorageService.userFileDownload(dto));
	}

	@GetMapping("/tapanda/portfolio/{iid}")
	public ResponseEntity<byte[]> downloadPortfolioFile(@PathVariable String iid, HttpServletResponse response) {
		if (!imageService.isExistByIid(iid)) {
			throw new ImageNotFoundException("사진을 찾을수 없습니다.", null, null);
		}
		FileResponseDto dto = new FileResponseDto(
				imageService.findByIid(iid).orElseThrow(() -> new ImageNotFoundException("사진을 찾을수 없습니다.", null, null)));

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fileStorageService.portfolioFileDownload(dto));
	}

}
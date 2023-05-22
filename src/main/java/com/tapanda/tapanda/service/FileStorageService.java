package com.tapanda.tapanda.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.tapanda.tapanda.model.Image;
import com.tapanda.tapanda.requestDto.FileRequestDto;
import com.tapanda.tapanda.responseDto.FileResponseDto;

public interface FileStorageService {

	public FileRequestDto fileUpload(MultipartFile file);

	public FileRequestDto portfolioFileUpload(MultipartFile file);

	boolean portfolioFileDelete(Image image);

	public File fileDownload(FileResponseDto dto);
	
	public byte[] userFileDownload(FileResponseDto dto);

	boolean fileDelete(FileResponseDto dto);

	public FileRequestDto userFileUpload(MultipartFile file);

	boolean userFileDelete(Image beforeImage);

	public byte[] portfolioFileDownload(FileResponseDto dto);

	public FileRequestDto productFileUpload(MultipartFile file);

}
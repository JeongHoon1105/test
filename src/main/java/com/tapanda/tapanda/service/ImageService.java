package com.tapanda.tapanda.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.tapanda.tapanda.model.Image;

public interface ImageService {

	Boolean isExistByIid(String iid);

	Optional<Image> findByIid(String filename);

	Optional<Image> save(Image image);

	Optional<Image> registerImage(MultipartFile file, String pid, Long memberId);

	Optional<Image> update(Image image);

	void deleteById(Long imageId);


}
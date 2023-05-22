package com.tapanda.tapanda.service.Impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tapanda.tapanda.exception.ImageDuplicateException;
import com.tapanda.tapanda.exception.ImageNotFoundException;
import com.tapanda.tapanda.model.Image;
import com.tapanda.tapanda.repository.ImageRepository;
import com.tapanda.tapanda.service.FileStorageService;
import com.tapanda.tapanda.service.ImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final FileStorageService fileStorageService;

    @Override
    public Optional<Image> findByIid(String filename) {
        if (!isExistByIid(filename)) {
            throw new ImageNotFoundException("사진을 찾을수 없습니다.", null, null);
        }
        return imageRepository.findByIid(UUID.fromString(filename));
    }
    
    @Override
    public Optional<Image> update(Image image) {
        // TODO Auto-generated method stub
        if (!isExistByIid(image.getIid().toString())) {
            throw new ImageNotFoundException("사진을 찾을수 없습니다.", null, image.getIid());
        }
        Image imageSaved = imageRepository.save(image);
        return Optional.ofNullable(imageSaved);
    }

    @Override
    public Optional<Image> save(Image image) {
        // TODO Auto-generated method stub
        if (isExistByIid(image.getIid().toString())) {
            throw new ImageDuplicateException("사진을 복제합니다", null, null);
        }
        Image imageSaved = imageRepository.save(image);
        return Optional.ofNullable(imageSaved);
    }

    @Override
    public Boolean isExistByIid(String iid) {
        // TODO Auto-generated method stub
        return imageRepository.existsByIid(UUID.fromString(iid));
    }

	@Override
	public Optional<Image> registerImage(MultipartFile file, String pid, Long memberId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

    @Override
    public void deleteById(Long imageId) {
        imageRepository.deleteById(imageId);
    }
}
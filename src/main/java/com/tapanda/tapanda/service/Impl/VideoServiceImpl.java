package com.tapanda.tapanda.service.Impl;

import com.tapanda.tapanda.exception.ProductNotFoundException;
import com.tapanda.tapanda.model.Product;
import com.tapanda.tapanda.model.Video;
import com.tapanda.tapanda.repository.ProductRepository;
import com.tapanda.tapanda.repository.VideoRepository;
import com.tapanda.tapanda.requestDto.VideoRequestDto;
import com.tapanda.tapanda.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final ProductRepository productRepository;
    private final VideoRepository videoRepository;

    @Override
    public Optional<Video> registerVideo(VideoRequestDto request, Long productId) {

        Optional<Product> productOptional = productRepository.findById(productId);
        Product product = productOptional.orElseThrow(() -> new ProductNotFoundException("해당 서비스를 찾을수 없습니다.", null, null));

        Video video = videoRepository.save(request.toEntity(product));

        return Optional.ofNullable(video);
    }
}

package com.tapanda.tapanda.service;

import com.tapanda.tapanda.model.Video;
import com.tapanda.tapanda.requestDto.VideoRequestDto;

import java.util.Optional;

public interface VideoService {

    Optional<Video> registerVideo(VideoRequestDto request, Long productId);
}
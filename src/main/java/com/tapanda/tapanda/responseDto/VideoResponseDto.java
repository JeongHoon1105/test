package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Video;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class VideoResponseDto {

    private String url;
    private Boolean videoFirst;

    public VideoResponseDto(Video video) {

        this.url = video.getUrl();
        this.videoFirst = video.getVideoFirst();
    }
}

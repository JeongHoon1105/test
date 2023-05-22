package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Image;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ImageResponseDto {

    private String iid;
    private String name;
    private String url;
    private Long blogArticleId;

    public ImageResponseDto(Image image) {
        this.iid = image.getIid().toString();
        this.name = image.getName();
        this.url = image.getUrl();
        this.blogArticleId = image.getBlogArticleId();
    }
}

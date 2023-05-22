package com.tapanda.tapanda.requestDto;

import com.tapanda.tapanda.model.Product;
import com.tapanda.tapanda.model.Video;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "비디오 DTO")
@RequiredArgsConstructor
public class VideoRequestDto {

    @ApiModelProperty(value = "비디오 URL")
    private String url;
    
    @ApiModelProperty(value = "이미지보다 비디오 우선 표시")
    private Boolean videoFirst;

    public Video toEntity(Product product) {

        return Video.builder()
                .url(url)
                .videoFirst(videoFirst)
                .product(product)
                .build();
    }
}

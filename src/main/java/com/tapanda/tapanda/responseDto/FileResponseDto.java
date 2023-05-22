package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileResponseDto {

    private String iid;

    private String name;

    private String url;

    public FileResponseDto(Image image) {
        super();
        this.iid = image.getIid().toString();
        this.name = image.getName();
        this.url = image.getUrl();
    }
}
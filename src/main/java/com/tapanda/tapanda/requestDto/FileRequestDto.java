package com.tapanda.tapanda.requestDto;

import java.util.UUID;

import com.tapanda.tapanda.model.Image;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "IMAGE RequestDTO")
@AllArgsConstructor
@NoArgsConstructor
public class FileRequestDto {

    private String iid;

    private String name;

    private String url;

    private int order_;

    public Image toEntity() {
        // TODO Auto-generated method stub
        return Image.builder().iid(UUID.fromString(iid)).name(name).url(url).build();
    }
}
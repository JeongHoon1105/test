package com.tapanda.tapanda.requestDto;

import javax.validation.constraints.NotBlank;

import com.tapanda.tapanda.model.Notice;
import com.tapanda.tapanda.model.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "공지사항 DTO")
@AllArgsConstructor
public class NoticeAdminRequestDto {

    @NotBlank(message = "제목을 입력해주세요.")
    @ApiModelProperty(value = "제목", required = true, allowableValues = "String")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    @ApiModelProperty(value = "내용", required = true, allowableValues = "String")
    private String content;

    public Notice toEntity(User user) {
        return Notice.builder().title(title).content(content).user(user).build();
    }
}
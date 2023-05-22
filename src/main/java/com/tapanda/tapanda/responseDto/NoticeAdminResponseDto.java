package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Notice;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class NoticeAdminResponseDto {

    private Long noticeId;
    private String title;
    private String content;

    public NoticeAdminResponseDto(Notice Notice) {

        this.noticeId = Notice.getNoticeId();
        this.title = Notice.getTitle();
        this.content = Notice.getContent();
    }
}
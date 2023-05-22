package com.tapanda.tapanda.service;

import java.util.List;
import java.util.Optional;

import com.tapanda.tapanda.model.Notice;
import com.tapanda.tapanda.requestDto.NoticeAdminRequestDto;

import javax.validation.Valid;

public interface NoticeService {

    Optional<Notice> registerNotice(@Valid NoticeAdminRequestDto request, Long userId);

    Optional<Notice> updateNotice(@Valid NoticeAdminRequestDto request, Long noticeId);

    void deleteByNoticeId(Long noticeId);

    List<Notice> findAll();

    Optional<Notice> findByNoticeId(Long noticeId);

    Boolean existsByNoticeId(Long noticeId);
}
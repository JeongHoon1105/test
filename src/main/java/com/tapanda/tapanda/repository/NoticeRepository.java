package com.tapanda.tapanda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tapanda.tapanda.model.Notice;


public interface NoticeRepository extends JpaRepository<Notice, Long> {

    Optional<Notice> findByNoticeId(Long noticeId);

    Boolean existsByNoticeId(Long noticeId);
}
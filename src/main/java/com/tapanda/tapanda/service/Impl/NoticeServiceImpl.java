package com.tapanda.tapanda.service.Impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.tapanda.tapanda.exception.UserNotFoundException;
import com.tapanda.tapanda.exception.NoticeNotFoundException;
import com.tapanda.tapanda.model.User;
import com.tapanda.tapanda.model.Notice;
import com.tapanda.tapanda.repository.NoticeRepository;
import com.tapanda.tapanda.requestDto.NoticeAdminRequestDto;
import com.tapanda.tapanda.service.UserService;
import com.tapanda.tapanda.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private  final UserService userService;

    @Override
    public Optional<Notice> registerNotice(@Valid NoticeAdminRequestDto request, Long userId) {
        // TODO Auto-generated method stub
        Optional<User> userOptional = userService.findById(userId);
        User user = userOptional.orElseThrow(() -> new UserNotFoundException("등록되지 않은 사용자입니다.", null, null));

        Notice notice = noticeRepository.save(request.toEntity(user));
        return Optional.ofNullable(notice);
    }

    @Override
    public Optional<Notice> updateNotice(@Valid NoticeAdminRequestDto request, Long noticeId) {
        // TODO Auto-generated method stub
        Notice notice = findByNoticeId(noticeId).orElseThrow(() -> new NoticeNotFoundException("해당 공지사항을 찾을수 없습니다.", null, null));

        notice.update(request.getTitle(), request.getContent());
        return Optional.ofNullable(noticeRepository.save(notice));
    }

    @Override
    public void deleteByNoticeId(Long noticeId) {
        // TODO Auto-generated method stub
        Notice notice = noticeRepository.findByNoticeId(noticeId)
                .orElseThrow(() -> new NoticeNotFoundException("해당 공지사항을 찾을수 없습니다.", null, null));
        noticeRepository.delete(notice);
    }

    @Override
    public List<Notice> findAll() {
        // TODO Auto-generated method stub
        return noticeRepository.findAll();
    }

    @Override
    public Optional<Notice> findByNoticeId(Long noticeId) {
        // TODO Auto-generated method stub
        if (!existsByNoticeId(noticeId)) {
            throw new NoticeNotFoundException("해당 공지사항을 찾을 수 없습니다.", null, null);
        }
        return noticeRepository.findByNoticeId(noticeId);
    }

    @Override
    public Boolean existsByNoticeId(Long noticeId) {
        // TODO Auto-generated method stub
        return noticeRepository.existsByNoticeId(noticeId);
    }
}
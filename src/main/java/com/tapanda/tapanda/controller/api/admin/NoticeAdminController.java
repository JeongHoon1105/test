package com.tapanda.tapanda.controller.api.admin;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tapanda.tapanda.exception.NoticeRegisterException;
import com.tapanda.tapanda.exception.NoticeNotFoundException;
import com.tapanda.tapanda.exception.NoticeUpdateException;
import com.tapanda.tapanda.model.Notice;
import com.tapanda.tapanda.model.User;
import com.tapanda.tapanda.payload.ApiResponse;
import com.tapanda.tapanda.requestDto.NoticeAdminRequestDto;
import com.tapanda.tapanda.responseDto.NoticeAdminResponseDto;
import com.tapanda.tapanda.service.NoticeService;
import com.tapanda.tapanda.validator.NoticeAdminValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/notice")
@Api(value = "관리자 공지사항 Rest API")
@RequiredArgsConstructor
public class NoticeAdminController {

    private final NoticeService noticeService;
    private final NoticeAdminValidator noticeValid;


    @PostMapping(value = "/register")
    @ApiOperation(value = "공시자항 등록")
    public ResponseEntity<?> registerNotice(@AuthenticationPrincipal User user,
                                            @Valid @RequestBody NoticeAdminRequestDto request,
                                            BindingResult result) {

        noticeValid.validate(request, result);
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }
        Long userId = user.getId();
        // System.out.println("User ID: " + userId);
        return noticeService.registerNotice(request, userId)
                .map(notice -> ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "공지사항을 등록하였습니다.")))
                .orElseThrow(() -> new NoticeRegisterException(request.getTitle(), "공지사항 등록에 실패하였습니다.", null));
    }

    @PutMapping(value = "/{notice_id}")
    @ApiOperation(value = "공지사항 수정")
    public ResponseEntity<?> noticeUpdate(@Valid @RequestBody NoticeAdminRequestDto request,
                                          @PathVariable("notice_id") Long noticeId) {
        return noticeService.updateNotice(request, noticeId)
                .map(notice -> ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "공지사항을 수정하였습니다.")))
                .orElseThrow(() -> new NoticeUpdateException(request.getTitle(), "공지사항 수정에 실패하였습니다.", null));
    }

    @DeleteMapping(value = "/{notice_id}")
    @ApiOperation(value = "공지사항 삭제")
    public ResponseEntity<?> noticeDelete(@PathVariable("notice_id") Long noticeId) {
        noticeService.deleteByNoticeId(noticeId);
        return ResponseEntity.ok(new ApiResponse(true, "공지사항을 삭제하였습니다."));
    }

    @GetMapping(value = "/all")
    @ApiOperation(value = "공지사항 목록")
    public ResponseEntity<?> getNoticeList() {
        List<NoticeAdminResponseDto> noticeDtoList = noticeService.findAll().stream().map(
                notice -> new NoticeAdminResponseDto(notice)).collect(Collectors.toList());
        return ResponseEntity.ok(noticeDtoList);
    }

    @GetMapping(value = "/{notice_id}")
    @ApiOperation(value = "공지사항 내용")
    public ResponseEntity<?> getNotice(@PathVariable("notice_id") Long noticeId) {
        Notice notice = noticeService.findByNoticeId(noticeId).orElseThrow(
                () -> new NoticeNotFoundException("해당 공지사항을 찾을 수 없습니다.", null, null));
        NoticeAdminResponseDto noticeDto = new NoticeAdminResponseDto(notice);
        return ResponseEntity.ok(noticeDto);
    }
}
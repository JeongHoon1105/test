package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Faq;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
public class FaqResponseDto {

    private String question;

    private String answer;

    public FaqResponseDto(Faq faq) {

        this.question = faq.getQuestion();
        this.answer = faq.getAnswer();
    }
}

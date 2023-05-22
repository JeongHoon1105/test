package com.tapanda.tapanda.requestDto;

import com.tapanda.tapanda.model.Faq;
import com.tapanda.tapanda.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@ApiModel(value = "FAQ DTO")
@RequiredArgsConstructor
public class FaqRequestDto {

    @ApiModelProperty(value = "FAQ 질문")
    private String question;

    @ApiModelProperty(value = "FAQ 답변")
    private String answer;

    public Faq toEntity(Product product) {

        return Faq.builder()
                .question(question)
                .answer(answer)
                .product(product)
                .build();
    }
}

package com.tapanda.tapanda.requestDto;

import java.time.Instant;

import com.tapanda.tapanda.model.Qualification;
import com.tapanda.tapanda.model.Seller;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "자격 DTO")
@AllArgsConstructor
public class QualificationRequestDto {
	
    private String name;

    private String date;
    
    public Qualification toEntity(Seller seller, Instant date) {
		// TODO Auto-generated method stub
		return Qualification.builder().name(name).date(date).seller(seller).build();
	}
}

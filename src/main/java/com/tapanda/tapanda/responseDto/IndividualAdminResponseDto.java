package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.enum_.Gender;
import com.tapanda.tapanda.model.Individual;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class IndividualAdminResponseDto {
    private Long id;

    private String name;

    private String address;

    private String birth;

    private String gender;

    private String classitication;

    public IndividualAdminResponseDto(Individual individual) {
        this.id = individual.getId();
        this.name = individual.getName();
        this.address = individual.getAddress();
        if (individual.getBirth() != null) {
            this.birth = individual.getBirth().toString();
        }
        if (individual.getGender() != null) {
            this.gender = individual.getGender().toString();
        }
        this.classitication = individual.getClassitication();
    }
}

package com.tapanda.tapanda.requestDto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;

import com.tapanda.tapanda.annotation.DateValid;
import com.tapanda.tapanda.enum_.Gender;
import com.tapanda.tapanda.enum_.Sectors;
import com.tapanda.tapanda.model.CorporationInfo;
import com.tapanda.tapanda.model.Seller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "법인 DTO")
@AllArgsConstructor
public class CorpInfoRequestDto {

	@NotBlank(message = "법인 이름을 입력하세요.")
	@ApiModelProperty(value = "법인 이름", required = true, allowableValues = "String")
	private String companyName;

	@NotBlank(message = "후리가나를 입력하세요.")
	@ApiModelProperty(value = "후리가나", required = true, allowableValues = "String")
	private String corporateName;

	@NotBlank(message = "법인 번호를 입력하세요.")
	@ApiModelProperty(value = "법인 번호", required = true, allowableValues = "String")
	private String corporateNumber;

	@NotBlank(message = "대표자 이름 입력하세요.")
	@ApiModelProperty(value = "사용자 주소", required = true, allowableValues = "String")
	private String representativeName;

	@ApiModelProperty(value = "대표자 생년월일", required = false)
	@DateValid
	private String representBirthDate;

	@NotBlank(message = "등록 위치를 입력하세요.")
	@ApiModelProperty(value = "등록 위치", required = true, allowableValues = "String")
	private String location;

	@NotBlank(message = "우편번호를 입력하세요")
	@ApiModelProperty(value = "우편번호", required = true, allowableValues = "String")
	private String zipCode;

	@NotBlank(message = "대표 전화번호를 입력하세요")
	@ApiModelProperty(value = "대표 전화번호", required = true, allowableValues = "String")
	private String representPhone;

	@NotBlank(message = "존재하지 않는 날짜는 등록할 수 없습니다.")
	@ApiModelProperty(value = "설립년월일", required = true, allowableValues = "String")
	@DateValid
	private String anniversary;

	@NotBlank(message = "자본금을 입력하세요.")
	@ApiModelProperty(value = "자본금", required = true, allowableValues = "Integer")
	private Integer capital;

	@NotBlank(message = "직원 수를 입력하세요.")
	@ApiModelProperty(value = "직원 수", required = true, allowableValues = "Integer")
	private Integer employeesNumber;

	@NotBlank(message = "연매출 입력하세요.")
	@ApiModelProperty(value = "연매출", required = true, allowableValues = "Integer")
	private Integer annualSales;

	@NotBlank(message = "업종을 선택하세요.")
	@ApiModelProperty(value = "업종", required = true, allowableValues = "String")
	private String sector;

	@NotBlank(message = "비즈니스 내용을 입력하세요.")
	@ApiModelProperty(value = "비즈니스 내용", required = true, allowableValues = "String")
	private String workDetail;

	@ApiModelProperty(value = "회사 URL", required = false)
	private String companyUrl;

	@NotBlank(message = "사용자 이름을 입력해주세요.")
	@ApiModelProperty(value = "사용자 이름", required = true, allowableValues = "String")
	private String userName;

	@NotBlank(message = "사용자 주소을 입력해주세요.")
	@ApiModelProperty(value = "사용자 주소", required = true, allowableValues = "String")
	private String address;

	@ApiModelProperty(value = "사용자 생년", required = false)
	@DateValid
	private String birthDate;

	@ApiModelProperty(value = "사용자 성별", required = false)
	private String gender;

	public CorporationInfo toEntity(Seller seller, Instant anniversary, Instant birth, Instant rBirth) {
		return CorporationInfo.builder().address(address).anniversary(anniversary).annualSales(annualSales)
				.birthDate(birth).capital(capital).companyName(companyName).companyUrl(companyUrl)
				.corporateName(corporateName).corporateNumber(corporateNumber).employeesNumber(employeesNumber)
				.gender(Gender.valueOf(gender)).location(location).representativeName(representativeName)
				.representBirthDate(rBirth).representPhone(representPhone).sector(Sectors.valueOf(sector))
				.userName(userName).workDetail(workDetail).zipCode(zipCode).seller(seller).build();
	}

}
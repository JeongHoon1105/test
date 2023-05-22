package com.tapanda.tapanda.responseDto;

import java.time.Instant;

import com.tapanda.tapanda.model.CorporationInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CorpInfoResponseDto {
	
	private Long id;
	
	private String companyName;

	private String corporateName;

	private String corporateNumber;

	private String representativeName;

	private String representBirthDate;

	private String location;

	private String zipCode;

	private String representPhone;

	private Instant anniversary;

	private Integer capital;

	private Integer employeesNumber;

	private Integer annualSales;
	
	private String sector;

	private String workDetail;

	private String companyUrl;

	private String userName;

	private String address;

	private String birthDate;

	private String gender;

	public CorpInfoResponseDto(CorporationInfo corpInfo) {
		this.id = corpInfo.getCorpInfoId();
		this.companyName = corpInfo.getCompanyName();
		this.corporateName = corpInfo.getCorporateName();
		this.corporateNumber = corpInfo.getCorporateNumber();
		this.representativeName = corpInfo.getRepresentativeName();
		this.representBirthDate = corpInfo.getRepresentBirthDate().toString();
		this.location = corpInfo.getLocation();
		this.zipCode = corpInfo.getZipCode();
		this.representPhone = corpInfo.getRepresentPhone();
		this.anniversary = corpInfo.getAnniversary();
		this.capital = corpInfo.getCapital();
		this.employeesNumber = corpInfo.getEmployeesNumber();
		this.annualSales = corpInfo.getAnnualSales();
		this.sector = corpInfo.getSector().toString();
		this.workDetail = corpInfo.getWorkDetail();
		this.companyUrl = corpInfo.getCompanyUrl();
		this.userName = corpInfo.getUserName();
		this.address = corpInfo.getAddress();
		this.birthDate = corpInfo.getBirthDate().toString();
		this.gender = corpInfo.getGender().toString();
	}
	
}
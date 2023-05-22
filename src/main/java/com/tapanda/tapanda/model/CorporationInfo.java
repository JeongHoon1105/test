package com.tapanda.tapanda.model;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.tapanda.tapanda.enum_.Gender;
import com.tapanda.tapanda.enum_.Sectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "seller_corporation_info")
@NoArgsConstructor
@AllArgsConstructor
public class CorporationInfo {

	@Id
	@Column(name = "CORPORATION_INFO_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "corporation_info_seq")
	@SequenceGenerator(name = "corporation_info_seq", allocationSize = 1)
	private Long corpInfoId;
	
	@Column(name = "COMPANY_NAME", nullable = false)
	private String companyName;
	
	@Column(name = "CORPORATE_NAME", nullable = false)
	private String corporateName;
	
	@Column(name = "CORPORATE_NUMBER", nullable = false)
	private String corporateNumber;
	
	@Column(name = "REPRESENT_NAME", nullable = false)
	private String representativeName;
	
	@Column(name = "REPRESENT_BIRTH_DATE")
	private Instant representBirthDate;
	
	@Column(name = "COMPANY_LOCATION", nullable = false)
	private String location;
	
	@Column(name = "ZIP_CODE", nullable = false)
	private String zipCode;

	@Column(name = "PHONE", nullable = false)
	private String representPhone;
	
	@Column(name = "ANNIVERSARY", nullable = false)
	private Instant anniversary;
	
	@Column(name = "CAPITAL", nullable = false)
	private Integer capital;
	
	@Column(name = "EMPLOYEES", nullable = false)
	private Integer employeesNumber;
	
	@Column(name = "ANNUAL_SALES", nullable = false)
	private Integer annualSales;
	
	@Column(name = "SECTOR", nullable = false)
	@Enumerated(EnumType.STRING)
	private Sectors sector;
	
	@Column(name = "WORK_DETAIL", nullable = false)
	private String workDetail;
	
	@Column(name = "COMPANY_URL")
	private String companyUrl;
	
	@Column(name = "USER_NAME", nullable = false)
	private String userName;
	
	@Column(name = "USER_ADDRESS", nullable = false)
	private String address;
	
	@Column(name = "USER_BIRTH_DATE", nullable = false)
	private Instant birthDate;
	
	@Column(name = "USER_GENDER", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
	private Seller seller;

	@Builder
	public CorporationInfo(String companyName, String corporateName, String corporateNumber, String representativeName,
			Instant representBirthDate, String location, String zipCode, String representPhone,
			Instant anniversary, Integer capital, Integer employeesNumber, Sectors sector, String workDetail,
			String companyUrl, String userName, String address, Instant birthDate, Gender gender, Integer annualSales, Seller seller) {
		super();
		this.companyName = companyName;
		this.corporateName = corporateName;
		this.corporateNumber = corporateNumber;
		this.representativeName = representativeName;
		this.representBirthDate = representBirthDate;
		this.location = location;
		this.zipCode = zipCode;
		this.representPhone = representPhone;
		this.anniversary = anniversary;
		this.capital = capital;
		this.employeesNumber = employeesNumber;
		this.sector = sector;
		this.workDetail = workDetail;
		this.companyUrl = companyUrl;
		this.userName = userName;
		this.address = address;
		this.birthDate = birthDate;
		this.gender = gender;
		this.annualSales = annualSales;
		this.seller = seller;
	}
	
	public void update(String companyName, String corporateName, String corporateNumber, String representativeName,
			Instant representBirthDate, String location, String zipCode, String representPhone,
			Instant anniversary, Integer capital, Integer employeesNumber, Sectors sector, String workDetail,
			String companyUrl, String userName, String address, Instant birthDate, Gender gender, Integer annualSales) {
		this.companyName = companyName;
		this.corporateName = corporateName;
		this.corporateNumber = corporateNumber;
		this.representativeName = representativeName;
		this.representBirthDate = representBirthDate;
		this.location = location;
		this.zipCode = zipCode;
		this.representPhone = representPhone;
		this.anniversary = anniversary;
		this.capital = capital;
		this.employeesNumber = employeesNumber;
		this.sector = sector;
		this.workDetail = workDetail;
		this.companyUrl = companyUrl;
		this.userName = userName;
		this.address = address;
		this.birthDate = birthDate;
		this.gender = gender;
		this.annualSales = annualSales;
	}
	
	
}
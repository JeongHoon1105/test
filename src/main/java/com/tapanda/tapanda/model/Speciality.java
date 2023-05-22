package com.tapanda.tapanda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "seller_specialities")
@NoArgsConstructor
@AllArgsConstructor
public class Speciality {
	@Id
	@Column(name = "SPEC_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seller_spec_seq")
	@SequenceGenerator(name = "seller_spec_seq", allocationSize = 1)
	private Long specId;
	@Column(name = "DETAIL")
	private String detail;
	@Column(name = "MINIMAL_PRICE")
	private Long minPrice;
	@Column(name = "DESCRIPTION")
	private String description;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CATEGORY_ID")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "SELLER_ID")
	private Seller seller;

	@Builder
	public Speciality(String detail, Long minPrice, String description, Seller seller, Category category) {
		super();
		this.detail = detail;
		this.minPrice = minPrice;
		this.description = description;
		this.seller = seller;
		this.category = category;
	}

	public void update(String detail, Long minPrice, String description, Category category) {
		this.detail = detail;
		this.minPrice = minPrice;
		this.description = description;
		this.category = category;
	}

}

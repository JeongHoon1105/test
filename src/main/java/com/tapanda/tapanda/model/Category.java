package com.tapanda.tapanda.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "SERVICE_CATEGORY")
@NoArgsConstructor
@AllArgsConstructor
public class Category {

	@Id
	@Column(name = "SERVICE_CATEGORY_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_category_seq")
	@SequenceGenerator(name = "service_category_seq", allocationSize = 1)
	private Long id;

	@Column(name = "CATEGORY_NAME")
	private String name;

	@Column(name = "PARENT_CODE")
	private String parentCode;

	@Column(name = "CATEGORY_CODE", unique = true)
	private String code;

	@Column(name = "SEQ")
	private Long seq;

	@OneToMany(mappedBy = "category")
	private List<Portfolio> portfolio;

	@OneToMany(mappedBy = "category")
	private List<Product> product;

	@Builder
	public Category(String name, String code, String parentCode, Long seq) {
		super();
		this.name = name;
		this.code = code;
		this.parentCode = parentCode;
		this.seq = seq;
	}

	public void update(String name, String code, String parentCode) {
		this.name = name;
		this.code = code;
		this.parentCode = parentCode;
	}

}
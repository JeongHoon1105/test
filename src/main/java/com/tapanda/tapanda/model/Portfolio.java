package com.tapanda.tapanda.model;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "seller_portfolio")
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {

	@Id
	@Column(name = "PORT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seller_port_seq")
	@SequenceGenerator(name = "seller_port_seq", allocationSize = 1)
	private Long portId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CATEGORY_ID")
	private Category category;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "DETAIL")
	private String detail;

	@Column(name = "UPDATE_DATE")
	private Instant upDate;

	@Column(name = "YOUTUBE_LINK")
	private String youtubeLink;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "IMAGE_ID", referencedColumnName = "IMAGE_ID")
	private Image image;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SELLER_ID")
	private Seller seller;

	@Builder
	public Portfolio(Category category, String title, String detail, Instant upDate, String youtubeLink, Image image,
			Seller seller) {
		super();
		this.category = category;
		this.title = title;
		this.detail = detail;
		this.upDate = upDate;
		this.youtubeLink = youtubeLink;
		this.image = image;
		this.seller = seller;
	}
	
	public void update(Category category, String title, String detail, Instant upDate, String youtubeLink, Image image) {
		this.category = category;
		this.title = title;
		this.detail = detail;
		this.upDate = upDate;
		this.youtubeLink = youtubeLink;
		this.image = image;
	}

}

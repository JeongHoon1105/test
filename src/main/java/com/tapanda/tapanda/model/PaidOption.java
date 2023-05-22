package com.tapanda.tapanda.model;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@Entity(name = "Paid_Option")
@NoArgsConstructor
@AllArgsConstructor
public class PaidOption {

    @Id
    @Column(name = "PAID_OPTION_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paid_option_seq")
    @SequenceGenerator(name = "paid_option_seq", allocationSize = 1)
    private Long id;

    @Column(name = "DETAIL")
    private String detail;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "OPTION_STATUS", columnDefinition = "TINYINT")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Builder
    public PaidOption(String detail, Integer price, Boolean status, Product product) {

        super();
        this.detail = detail;
        this.price = price;
        this.status = status;
        this.product = product;
    }

    public void update(String detail, Integer price, Boolean status) {

        this.detail = detail;
        this.price = price;
        this.status = status;
    }
}
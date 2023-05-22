package com.tapanda.tapanda.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity(name = "seller_qualification")
@NoArgsConstructor
@AllArgsConstructor
public class Qualification {

    @Id
    @Column(name = "QUAL_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seller_qual_seq")
    @SequenceGenerator(name = "seller_qual_seq", allocationSize = 1)
    private Long qualId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DATE")
    private Instant date;

    @ManyToOne
    @JoinColumn(name = "SELLER_ID")
    private Seller seller;

    @Builder
    public Qualification (String name, Instant date, Seller seller) {
        super();
        this.name = name;
        this.date = date;
        this.seller = seller;
    }
    
    public void update (String name, Instant date) {
    	this.name = name;
    	this.date = date;
    }

}

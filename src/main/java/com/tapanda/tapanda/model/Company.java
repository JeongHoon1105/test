package com.tapanda.tapanda.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity(name = "seller_company_qualification")
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @Column(name = "COMPANY_QUAL_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "com_qual_id")
    @SequenceGenerator(name = "com_qual_id", allocationSize = 1)
    private Long comQualId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "START_DATE")
    private Instant startDate;

    @Column(name = "FINISH_DATE")
    private Instant finishDate;

    @Column(name = "DETAIL")
    private String detail;

    @ManyToOne
    @JoinColumn(name = "SELLER_ID")
    private Seller seller;

    @Builder
    public Company(String name, String position, Instant startDate, Instant finishDate, String detail, Seller seller) {
        super();
        this.name = name;
        this.position = position;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.detail = detail;
        this.seller = seller;
    }
    
    public void update (String name, String position, Instant startDate, Instant finishDate, String detail) {
        this.name = name;
        this.position = position;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.detail = detail;
    }
}

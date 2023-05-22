package com.tapanda.tapanda.model;

import com.tapanda.tapanda.enum_.AwardsCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity(name = "seller_awards_qualification")
@NoArgsConstructor
@AllArgsConstructor
public class Awards {

    @Id
    @Column(name = "AWARDS_QUAL_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "awards_qual_seq")
    @SequenceGenerator(name = "awards_qual_seq", allocationSize = 1)
    private Long awardsQualId;

    @Column(name = "AWARDS_CATEGORY")
    @Enumerated(EnumType.STRING)
    private AwardsCategory awardsCategory;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DATE")
    private Instant date;

    @ManyToOne
    @JoinColumn(name = "SELLER_ID")
    private Seller seller;

    @Builder
    public Awards(AwardsCategory awardsCategory, String title, Instant date, Seller seller) {
        super();
        this.awardsCategory = awardsCategory;
        this.title = title;
        this.date = date;
        this.seller = seller;
    }

    public void update(String title, Instant date, AwardsCategory awardsCategory) {
        this.title = title;
        this.date = date;
        this.awardsCategory = awardsCategory;
    }

}

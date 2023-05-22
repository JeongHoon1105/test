package com.tapanda.tapanda.model;

import com.tapanda.tapanda.enum_.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity(name = "INDIVIDUAL_INFO")
@NoArgsConstructor
@AllArgsConstructor
public class Individual {

    @Id
    @Column(name = "INDIVIDUAL_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "individual_seq")
    @SequenceGenerator(name = "individual_seq", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "BIRTH_DATE")
    private Instant birth;

    @Column(name = "GENDER")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "CLASSITICATION")
    private String classitication;

    @OneToOne
    @JoinColumn(name = "SELLER_ID", referencedColumnName = "SELLER_ID")
    private Seller seller;

    @Builder
    public Individual(String name, String address, Instant birth, Gender gender, String classitication, Seller seller) {
        super();
        this.name = name;
        this.address = address;
        this.birth = birth;
        this.gender = gender;
        this.classitication = classitication;
        this.seller = seller;
    }

    public void update(String name, String address, Instant birth, Gender gender, String classitication) {
        this.name = name;
        this.address = address;
        this.birth = birth;
        this.gender = gender;
        this.classitication = classitication;
    }

}

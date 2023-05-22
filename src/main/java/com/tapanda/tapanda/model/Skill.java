package com.tapanda.tapanda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.tapanda.tapanda.enum_.SkillLevel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.sql.Update;

@Getter
@Setter
@Entity(name = "seller_skill")
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @Column(name = "SKILL_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seller_skill_seq")
    @SequenceGenerator(name = "seller_skill_seq", allocationSize = 1)
    private Long skillId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "EXPERIENCE")
    private Integer experience;

    @Column(name = "SKILL_LEVEL")
    @Enumerated(EnumType.STRING)
    private SkillLevel skillLevel;

    @Column(name = "DETAIL")
    private String detail;

    @ManyToOne
    @JoinColumn(name = "SELLER_ID")
    private Seller seller;

    @Builder
    public Skill(String title, Integer experience, SkillLevel skillLevel, String detail, Seller seller) {
        super();
        this.title = title;
        this.experience = experience;
        this.skillLevel = skillLevel;
        this.detail = detail;
        this.seller = seller;
    }

    public void update(String title, Integer experience, SkillLevel skillLevel, String detail) {
        this.title = title;
        this.experience = experience;
        this.skillLevel = skillLevel;
        this.detail = detail;
    }


}

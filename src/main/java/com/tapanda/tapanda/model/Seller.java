package com.tapanda.tapanda.model;

import com.tapanda.tapanda.enum_.JobStatus;
import lombok.*;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity(name = "SELLER")
@NoArgsConstructor
@AllArgsConstructor
public class Seller {

    @Id
    @Column(name = "SELLER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seller_seq")
    @SequenceGenerator(name = "seller_seq", allocationSize = 1)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    
    @OneToOne(mappedBy = "seller")
    private CorporationInfo corpInfo;

    @Column(name = "JOB_STATUS")
    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;
    
    @Column(name = "SELF_INTRODUCTION")
    private String selfIntro;

    @Column(name = "CATCHPHRASE")
    private String cPhrase;

    @Column(name = "SCHEDULE")
    private String schedule;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Portfolio> portfolio;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Skill> skill;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Speciality> speciality;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Qualification> qualification;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Company> company;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Awards> awards;

    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Individual individual;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> product;

    @Builder
    public Seller(User user, String selfIntro, String cPhrase, String schedule,
                  JobStatus jobStatus, List<Portfolio> portfolio, List<Skill> skill, List<Speciality> speciality,
                  List<Qualification> qualification, List<Company> company, List<Awards> awards, Individual individual) {
        super();
        this.user = user;
        this.selfIntro = selfIntro;
        this.cPhrase = cPhrase;
        this.schedule = schedule;
        this.jobStatus = jobStatus;
        this.portfolio = portfolio;
        this.skill = skill;
        this.speciality = speciality;
        this.qualification = qualification;
        this.company = company;
        this.awards = awards;
        this.individual = individual;
    }
    
	public void updateSelfInfo(String selfIntro, String cPhrase, String schedule, JobStatus jobStatus) {
		this.selfIntro = selfIntro;
		this.cPhrase = cPhrase;
		this.schedule = schedule;
		this.jobStatus = jobStatus;
	}


}

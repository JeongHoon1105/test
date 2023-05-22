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
@Entity(name = "FAQ")
@NoArgsConstructor
@AllArgsConstructor
public class Faq {

    @Id
    @Column(name = "FAQ_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "faq_seq")
    @SequenceGenerator(name = "faq_seq", allocationSize = 1)
    private Long id;

    @Column(name = "QUESTION")
    private String question;

    @Column(name = "ANSWER")
    private String answer;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Builder
    public Faq(String question, String answer, Product product) {
        super();
        this.question = question;
        this.answer = answer;
        this.product = product;
    }

    public void update(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
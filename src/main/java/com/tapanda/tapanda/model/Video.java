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

@Setter
@Getter
@Entity(name = "VIDEO")
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    @Id
    @Column(name = "VIDEO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "video_seq")
    @SequenceGenerator(name = "video_seq", allocationSize = 1)
    private Long id;

    @Column(name = "URL")
    private String url;

    @Column(name = "VIDEO_FIRST", columnDefinition = "TINYINT")
    private Boolean videoFirst;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Builder
    public Video(String url, Boolean videoFirst, Product product) {
        super();
        this.url = url;
        this.videoFirst = videoFirst;
        this.product = product;
    }

    public void update(String url, Boolean videoFirst) {
        this.url = url;
        this.videoFirst = videoFirst;
    }
}
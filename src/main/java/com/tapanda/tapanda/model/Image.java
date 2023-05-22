package com.tapanda.tapanda.model;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Type;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@Entity(name = "IMAGE")
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @Column(name = "IMAGE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_seq")
    @SequenceGenerator(name = "image_seq", allocationSize = 1)
    private Long imageId;

    @Column(name = "IID", unique=true)
    @Type(type = "uuid-char")
    private UUID iid;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL")
    private String url;

//    @ManyToOne
//    @JoinColumn(name = "BLOG_ARTICLE_ID")
//    private Article article;

    @Column(name = "BLOG_ARTICLE_ID")
    private Long blogArticleId;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    
    @Builder
    public Image(UUID iid, String name, String url, Product product, Long blogArticleId) {
        super();
        this.iid = iid;
        this.name = name;
        this.url = url;
        this.product = product;
        this.blogArticleId = blogArticleId;
    }

}
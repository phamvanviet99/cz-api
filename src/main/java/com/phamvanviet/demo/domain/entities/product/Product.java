package com.phamvanviet.demo.domain.entities.product;

import com.phamvanviet.demo.domain.entities.BaseEntity;
import com.phamvanviet.demo.domain.entities.category.Category;
import com.phamvanviet.demo.domain.entities.category.AttributeValue;
import com.phamvanviet.demo.domain.entities.comment.Comment;
import com.phamvanviet.demo.domain.entities.image.Image;
import com.phamvanviet.demo.domain.entities.rate.Rate;
import com.phamvanviet.demo.domain.entities.variant.Variant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String name;

    private Long unitPrice;

    @Column(length = 50)
    private String state;

    private double rate;

    private double discount;

    private Integer quantity;

    @Column(length = 150)
    private String shortDescription;

    private String description;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

    @OneToMany(mappedBy="product")
    private List<Rate> rates;

    @OneToMany(mappedBy="product")
    private List<Comment> comments;

    @OneToMany(mappedBy="product")
    private List<Image> images;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "variant_id")
    private Variant variant;

    @ManyToMany
    @JoinTable(
            name = "product_attribute_value",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_value_id"))
    private List<AttributeValue> attributeValues;

}

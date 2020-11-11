package com.phamvanviet.demo.domain.entities.category;

import com.phamvanviet.demo.domain.entities.BaseEntity;
import com.phamvanviet.demo.domain.entities.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "attribute_value")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AttributeValue extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String value;

    @ManyToOne
    @JoinColumn(name="attribute_id", nullable=false)
    private Attribute attribute;


    @ManyToMany(mappedBy = "attributeValues")
    List<Product> products;

    public AttributeValue(Integer id) {
        this.id = id;
    }

}

package com.phamvanviet.demo.domain.entities.category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.phamvanviet.demo.domain.entities.BaseEntity;
import com.phamvanviet.demo.domain.entities.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id",referencedColumnName = "id", nullable = true)
    @JsonBackReference
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Category> children = new ArrayList<>();

    private Integer level;

    @OneToMany(mappedBy="category", fetch = FetchType.LAZY)
    private List<Product> products;

    @OneToMany(mappedBy="category", fetch = FetchType.LAZY)
    private List<Attribute> attributes;
}

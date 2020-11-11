package com.phamvanviet.demo.domain.entities.category;

import com.phamvanviet.demo.domain.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity(name = "attribute")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Attribute extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy="attribute", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AttributeValue> attributeValues;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;



}

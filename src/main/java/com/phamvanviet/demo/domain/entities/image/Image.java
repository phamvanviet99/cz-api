package com.phamvanviet.demo.domain.entities.image;

import com.phamvanviet.demo.domain.entities.BaseEntity;
import com.phamvanviet.demo.domain.entities.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "tbl_image")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String link;

    @ManyToOne
    @JoinColumn(name="prduct_id", nullable=false)
    private Product product;
}

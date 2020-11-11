package com.phamvanviet.demo.domain.entities.rate;

import com.phamvanviet.demo.domain.entities.BaseEntity;
import com.phamvanviet.demo.domain.entities.product.Product;
import com.phamvanviet.demo.domain.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "rate")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rate extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer ratePoint;

    private String content;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

//    @ManyToOne
//    @JoinColumn(name="user_id", nullable=false)
//    private User user;
}

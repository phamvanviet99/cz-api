package com.phamvanviet.demo.domain.entities.comment;

import com.phamvanviet.demo.domain.entities.BaseEntity;
import com.phamvanviet.demo.domain.entities.product.Product;
import com.phamvanviet.demo.domain.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;


//    @ManyToOne
//    @JoinColumn(name="user_id", nullable=false)
//    private User user;
}

package com.phamvanviet.demo.domain.model;

import com.phamvanviet.demo.app.dtos.*;
import com.phamvanviet.demo.app.responses.AttributeValueResponse;
import com.phamvanviet.demo.app.responses.CategoryResponse;
import com.phamvanviet.demo.app.responses.ProductResponse;
import com.phamvanviet.demo.domain.entities.category.AttributeValue;
import com.phamvanviet.demo.domain.entities.category.Category;
import com.phamvanviet.demo.domain.entities.comment.Comment;
import com.phamvanviet.demo.domain.entities.product.Product;
import com.phamvanviet.demo.domain.entities.profile.Profile;
import com.phamvanviet.demo.domain.entities.rate.Rate;
import com.phamvanviet.demo.domain.entities.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ModelMapper {


    @Mapping(target = "products", source = "products")
    CategoryResponse categoriesToResponse(Category category);

    @Mapping(target = "attribute", source = "attribute.name")
    AttributeValueResponse attributeValuetoResponse(AttributeValue attributeValue);

    CommentDTO commentMapper(Comment comment);
    @Mappings({
            @Mapping(target = "attributeValues", source = "attributeValues")
    })
    ProductResponse productToResponse(Product product);

    ProfileDTO profileMapper(Profile profile);
    RateDTO rateMapper(Rate rate);
    UserDTO userMapper(User user);
}

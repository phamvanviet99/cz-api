package com.phamvanviet.demo.domain.services;

import com.phamvanviet.demo.app.dtos.ProductDTO;
import com.phamvanviet.demo.app.responses.ProductResponse;
import com.phamvanviet.demo.domain.entities.category.AttributeValue;
import com.phamvanviet.demo.domain.entities.category.Category;
import com.phamvanviet.demo.domain.entities.product.Product;
import com.phamvanviet.demo.domain.entities.user.User;
import com.phamvanviet.demo.domain.exception.NotFoundException;
import com.phamvanviet.demo.domain.model.ModelMapper;
import com.phamvanviet.demo.domain.model.TokenInfo;
import com.phamvanviet.demo.domain.repositories.CategoryRepository;
import com.phamvanviet.demo.domain.repositories.ProductRepository;
import com.phamvanviet.demo.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ProductService extends BaseService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity create(ProductDTO productDTO, String accessToken) {
        TokenInfo tokenInfo = isAuthen(accessToken);
        User user = userRepository.findById(tokenInfo.getUserId()).orElse(null);
        Category category = categoryRepository.getOne(productDTO.getCategoryId());
        if (Objects.isNull(category)) {
            throw new NotFoundException("Category Not Found!");
        }

        List<AttributeValue> attributeValues = new ArrayList<>();
        for (Integer id : productDTO.getAttributeValues()) {
            attributeValues.add(new AttributeValue(id));
        }

        Product product = Product.builder()
                .name(productDTO.getName())
                .unitPrice(productDTO.getUnitPrice())
                .state(productDTO.getState())
                .rate(productDTO.getRate())
                .discount(productDTO.getDiscount())
                .quantity(productDTO.getQuantity())
                .shortDescription(productDTO.getShortDescription())
                .description(productDTO.getDescription())
                .category(category)
                .build();
        product.setCreatedBy(user);
        product.setUpdatedBy(user);
        product.setAttributeValues(attributeValues);
        productRepository.save(product);
        return new ResponseEntity<>("created!", HttpStatus.OK);
    }

    public ResponseEntity update(ProductDTO productDTO, String accessToken, Long id) {
        TokenInfo tokenInfo = isAuthen(accessToken);
        Category category = categoryRepository.getOne(productDTO.getCategoryId());

        Product product = productRepository.findProductByIdAndAndCreatedById(id, tokenInfo.getUserId());
        if (product == null) {
            throw new NotFoundException("Product not found Id: " + id);
        }
        product.setName(productDTO.getName());
        product.setUnitPrice(productDTO.getUnitPrice());
        product.setState(productDTO.getState());
        product.setRate(productDTO.getRate());
        product.setDiscount(productDTO.getDiscount());
        product.setQuantity(productDTO.getQuantity());
        product.setShortDescription(productDTO.getShortDescription());
        product.setDescription(productDTO.getDescription());
        product.setCategory(category);
        productRepository.save(product);
        return new ResponseEntity<>("updated!", HttpStatus.OK);
    }

    public ResponseEntity delete(String accessToken, Long id) {
        TokenInfo tokenInfo = isAuthen(accessToken);
        Product product = productRepository.findProductByIdAndAndCreatedById(id, tokenInfo.getUserId());
        if (Objects.isNull(product))
            throw new NotFoundException("Product not found Id: " + id);
        productRepository.deleteById(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }

    public ResponseEntity getProduct(Integer offset, Integer limit, String accessToken, Integer id) {
        isAuthen(accessToken);
        Pageable pageable = PageRequest.of(offset,limit);
        Page<Product> products = productRepository.findProductByCategoryId(id, pageable);
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product item: products){
            productResponses.add(modelMapper.productToResponse(item));
        }
        return ResponseEntity.ok(productResponses);
    }
}

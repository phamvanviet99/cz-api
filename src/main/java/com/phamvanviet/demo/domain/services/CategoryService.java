package com.phamvanviet.demo.domain.services;

import com.phamvanviet.demo.app.dtos.CategoryDTO;
import com.phamvanviet.demo.app.responses.CategoryResponse;
import com.phamvanviet.demo.domain.entities.category.Category;
import com.phamvanviet.demo.domain.entities.user.User;
import com.phamvanviet.demo.domain.exception.NotFoundException;
import com.phamvanviet.demo.domain.model.ModelMapper;
import com.phamvanviet.demo.domain.model.TokenInfo;
import com.phamvanviet.demo.domain.repositories.CategoryRepository;
import com.phamvanviet.demo.domain.repositories.ProductRepository;
import com.phamvanviet.demo.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryService extends BaseService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;


    public ResponseEntity createCategory(CategoryDTO categoryDTO, String accessToken) {
        TokenInfo tokenInfo = isAuthen(accessToken);
        User user = userRepository.getOne(tokenInfo.getUserId());
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setLevel(categoryDTO.getLevel());
        category.setCreatedBy(user);
        if (Objects.nonNull(categoryDTO.getParentId())){
            Category parent = categoryRepository.findCategoryById(categoryDTO.getParentId());
            if(parent!=null) {
                category.setParent(parent);
            }
            else
                throw new NotFoundException("Category parent not Found!");
        }
        categoryRepository.save(category);
        return new ResponseEntity<>("created!", HttpStatus.OK);
    }

    public ResponseEntity update(CategoryDTO categoryDTO, String accessToken, Integer id) {
        TokenInfo tokenInfo = isAuthen(accessToken);
        User user = userRepository.getOne(tokenInfo.getUserId());
        Category category = categoryRepository.getOne(id);
        category.setName(categoryDTO.getName());
        category.setLevel(categoryDTO.getLevel());
        category.setUpdatedBy(user);
        if (Objects.nonNull(categoryDTO.getParentId())){
            Category parent = categoryRepository.findCategoryById(categoryDTO.getParentId());
            if(parent!=null) {
                category.setParent(parent);
            }
        }
        categoryRepository.save(category);
        return new ResponseEntity<>("updated!", HttpStatus.OK);
    }

    public ResponseEntity delete(String accessToken, Integer id) {
        isAuthen(accessToken);
        Category category = categoryRepository.getOne(id);
        categoryRepository.delete(category);
        return new ResponseEntity<>("deleted!", HttpStatus.OK);
    }

    public ResponseEntity getCategory(String accessToken){
        isAuthen(accessToken);
        List<Category> categories = categoryRepository.findCategoryByIdNotNull();
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category item: categories){
            if (item.getLevel()==1)
                categoryResponses.add(modelMapper.categoriesToResponse(item));
        }
        return ResponseEntity.ok(categoryResponses);

    }

//    public void recursiveTree(CategoryDTO categoryDTO, List<CategoryDTO> categoryList) {
////        productRepository.findProductByCategoryId(category.getId());
//        categoryList.add(categoryDTO);
//        if(categoryDTO.getChildren().size()>0){
//            for (Category c: category.getChildren()){
//                recursiveTree(category,categoryList);
//            }
//        }
//    }

}

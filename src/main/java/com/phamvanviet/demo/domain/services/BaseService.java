package com.phamvanviet.demo.domain.services;

import com.phamvanviet.demo.app.dtos.ProfileDTO;
import com.phamvanviet.demo.app.dtos.UserDTO;
import com.phamvanviet.demo.app.responses.ProductResponse;
import com.phamvanviet.demo.app.responses.UserResponse;
import com.phamvanviet.demo.domain.entities.product.Product;
import com.phamvanviet.demo.domain.entities.profile.Profile;
import com.phamvanviet.demo.domain.entities.user.User;
import com.phamvanviet.demo.domain.exception.NotFoundException;
import com.phamvanviet.demo.domain.model.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseService {

    @Autowired
    private CacheManagerService cacheManagerService;

    protected TokenInfo isAuthen(String accessToken) {
//        Authen authen = authenRepository.findByToken(accessToken);
//        accessToken = CacheKey.genKey(accessToken);
        TokenInfo tokenInfo = cacheManagerService.getToken(accessToken);
        if (tokenInfo == null)
            throw new NotFoundException("User not Found!");
        return tokenInfo;
    }

    protected UserDTO convertToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        if (user != null) {
            dto.setUserName(user.getUsername());
            dto.setPassword(user.getPassword());
            dto.setEmail(user.getEmail());
            return dto;
        } else return null;
    }

    protected UserResponse convertToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        if (user != null) {
            userResponse.setUserName(user.getUsername());
            userResponse.setEmail(user.getEmail());
            return userResponse;
        } else return null;
    }

    protected ProfileDTO convertToProfileDTO(Profile profile) {
        ProfileDTO dto = new ProfileDTO();
        if (profile != null) {
            dto.setAddress(profile.getAddress());
            dto.setPhoneNumber(profile.getPhoneNumber());
            dto.setGender(profile.getGender());
            dto.setName(profile.getName());
            return dto;
        } else return null;
    }

    protected ProductResponse convertToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setName(product.getName());
        productResponse.setUnitPrice(product.getUnitPrice());
        productResponse.setState(product.getState());
        productResponse.setRate(product.getRate());
        productResponse.setDiscount(product.getDiscount());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setShortDescription(product.getShortDescription());
        productResponse.setDescription(product.getDescription());
        return productResponse;
    }
}

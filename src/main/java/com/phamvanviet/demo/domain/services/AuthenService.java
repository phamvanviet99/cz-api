package com.phamvanviet.demo.domain.services;

import com.phamvanviet.demo.domain.entities.user.User;
import com.phamvanviet.demo.domain.exception.NotFoundException;
import com.phamvanviet.demo.domain.model.TokenInfo;
import com.phamvanviet.demo.domain.repositories.UserRepository;
import com.phamvanviet.demo.domain.utils.CacheKey;
import org.apache.commons.lang.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenService extends BaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheManagerService cacheManagerService;

    public ResponseEntity<?> getTokenByUserNameAndPassword(String userName, String password) {
        User user = userRepository.findByUsername(userName).orElse(null);
        if(!matchingPass(password, user.getPassword()))
            throw new NotFoundException("Wrong password!");
        if(user == null)
            throw new NotFoundException("User not found!");

        String token = generateToken();
        cacheManagerService.setToken(token,user.getId());
//        return CacheKey.genKey(token);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity deleteTokenWhenLogout(String accessToken) {
            isAuthen(accessToken);
            accessToken = CacheKey.genKey(accessToken);
            cacheManagerService.deleteToken(accessToken);
            return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    public ResponseEntity changePassword(String newPassword, String accessToken) {
//        User user = userRepository.findById(cacheManagerService.getToken(accessToken)).orElse(null);
        TokenInfo tokenInfo = isAuthen(accessToken);
        User user = userRepository.findById(tokenInfo.getUserId()).orElse(null);
        user.setPassword(convertPassword(newPassword));
        userRepository.save(user);
        return new ResponseEntity<>("changed!", HttpStatus.OK);
    }

    private String generateToken() {
        String tokens = RandomStringUtils.randomAlphabetic(32);
        return tokens;
    }

    private boolean matchingPass(String password, String otherPassword) {
        return BCrypt.checkpw(password, otherPassword);
//    return password.equals(convertPassword(otherPassword));
    }

    public static String convertPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }


}
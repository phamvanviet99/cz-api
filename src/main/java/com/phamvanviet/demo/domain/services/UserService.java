package com.phamvanviet.demo.domain.services;

import com.phamvanviet.demo.app.dtos.UserDTO;
import com.phamvanviet.demo.app.responses.UserResponse;
import com.phamvanviet.demo.domain.entities.profile.Profile;
import com.phamvanviet.demo.domain.entities.user.User;
import com.phamvanviet.demo.domain.exception.NotFoundException;
import com.phamvanviet.demo.domain.model.TokenInfo;
import com.phamvanviet.demo.domain.repositories.ProfileRepository;
import com.phamvanviet.demo.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService extends BaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheManagerService cacheManagerService;

    @Autowired
    private ProfileRepository profileRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public ResponseEntity createUser(UserDTO userDTO) {

//        profileRepository.save(profile);
//        User user = User.builder()
//                .username(userDTO.getUserName())
//                .password(AuthenService.convertPassword(userDTO.getPassword()))
//                .email(userDTO.getEmail())
//                .profile(profile)
//                .build();
        User user = new User();
        user.setUsername(userDTO.getUserName());
        user.setPassword(AuthenService.convertPassword(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        Profile profile = Profile.builder()
                .name(userDTO.getName())
                .user(user)
                .build();
        profile.setCreatedBy(user);
        profileRepository.save(profile);
        return new ResponseEntity<>("created!", HttpStatus.OK);
    }

    public ResponseEntity updateUser(UserDTO userDTO, String accessToken) {
//        Authen authen = authenRepository.findByToken(accessToken);
//        User user = userRepository.findById(cacheManagerService.getToken(accessToken)).orElse(null);
        TokenInfo tokenInfo = isAuthen(accessToken);
        User user = userRepository.findById(tokenInfo.getUserId()).orElse(null);
        if (user == null)
            throw new NotFoundException("User not found!");

        user.setUsername(userDTO.getUserName());
        user.setPassword(AuthenService.convertPassword(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        userRepository.save(user);
        return new ResponseEntity<>("updated!", HttpStatus.OK);
    }

    public ResponseEntity deleteUser(String accessToken) {
//        Authen authen = authenRepository.findByUserId(id);
//        authenRepository.delete(authen);
        TokenInfo tokenInfo = isAuthen(accessToken);
        User user = userRepository.findById(tokenInfo.getUserId()).orElse(null);
        if (user == null)
            throw new NotFoundException("User not found!");

        cacheManagerService.deleteToken(accessToken);
        userRepository.deleteById(tokenInfo.getUserId());
        return new ResponseEntity<>("deleted!", HttpStatus.OK);
    }
//
//    public void delete(long[] id) {
//        for (long item : id) {
//            Authen authen = authenRepository.findByUserId(item);
//            authenRepository.delete(authen);
//            userRepository.deleteById(item);
//        }
//    }

    public User findById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    public UserDTO findByUserName(String userName) {
        User user = userRepository.findByUsername(userName).orElse(null);
        return convertToUserDTO(user);
    }

    public UserResponse getUser(String accessToken) {
        TokenInfo tokenInfo = isAuthen(accessToken);
        User user = userRepository.getOne(tokenInfo.getUserId());
        return convertToUserResponse(user);
    }

//    private String getIdUserFromAccessToken(String accessToken) {
//        List<String> array = Arrays.asList(accessToken.split("_"));
//        return array.get(2);
//    }

}

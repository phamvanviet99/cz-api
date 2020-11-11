package com.phamvanviet.demo.domain.services;

import com.phamvanviet.demo.app.dtos.ProfileDTO;
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

@Service
public class ProfileService extends BaseService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity updateProfile(ProfileDTO profileDTO, String accessToken) {
        TokenInfo tokenInfo = isAuthen(accessToken);
        User user = userRepository.findById(tokenInfo.getUserId()).orElse(null);
        Profile profile = profileRepository.findById(user.getProfile().getId()).orElse(null);
        if (profile == null)
            throw new NotFoundException("User not found!");
        profile.setAddress(profileDTO.getAddress());
        profile.setGender(profileDTO.getGender());
        profile.setPhoneNumber(profileDTO.getPhoneNumber());
        profile.setName(profileDTO.getName());
        profile.setUpdatedBy(user);
        profileRepository.save(profile);
        return new ResponseEntity<>("profile updated!", HttpStatus.OK);

    }
}

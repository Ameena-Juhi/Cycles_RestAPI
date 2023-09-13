package com.project.RestApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.RestApi.entity.AppUser;
import com.project.RestApi.repository.UserRepository;

@Service
public class DomainUserService {

    private static final String ENCODING_STRATEGY = "{bcrypt}";

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    
    public DomainUserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<AppUser> getByName(String name) {
        return Optional.ofNullable(userRepository.findByUsername(name));
    }

    private String prefixEncodingStrategyAndEncode(String rawString) {
        return ENCODING_STRATEGY + passwordEncoder.encode(rawString);
    }

    public AppUser save(String username, String password) {
        AppUser user = new AppUser();
        user.setName(username); 
        user.setPassword(prefixEncodingStrategyAndEncode(password));
        return userRepository.save(user);
    }


}


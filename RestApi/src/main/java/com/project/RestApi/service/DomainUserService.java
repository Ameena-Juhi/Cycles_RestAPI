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

    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public DomainUserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<AppUser> authenticate(String username, String password) throws Exception {
        Optional<AppUser> optUser = userRepository.findByName(username);
        if (optUser.isEmpty()) {
            throw new Exception("User not found");
        }
        if (!optUser.get().getPassword().equals(password)) {
            return Optional.empty();
        }
        return optUser;
    }

    public AppUser create(AppUser user) {
        user.setPassword("{bcrypt}" + passwordEncoder.encode(user.getPassword()));
        //user.setPassword("{noop}" + user.getPassword());
        return userRepository.save(user);
    }

    public Optional<AppUser> getById(int id) {
        return userRepository.findById(id);
    }

    public Optional<AppUser> getByName(String name) {
        return userRepository.findByName(name);
    }

    
}

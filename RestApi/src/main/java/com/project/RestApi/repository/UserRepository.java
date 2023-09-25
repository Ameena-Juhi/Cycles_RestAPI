package com.project.RestApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.RestApi.entity.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

		 public  Optional<User> findByName(String name);
		 
		 public boolean existsByName(String name);

		

}

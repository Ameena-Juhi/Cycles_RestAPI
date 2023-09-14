package com.project.RestApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.RestApi.entity.AppUser;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

		 public  Optional<AppUser> findByName(String name);
		 
		 public boolean existsByName(String name);

		

}

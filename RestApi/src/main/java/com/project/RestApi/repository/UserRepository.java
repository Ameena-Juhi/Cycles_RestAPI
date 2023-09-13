package com.project.RestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.RestApi.entity.AppUser;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<AppUser, Integer> {
	

		  boolean existsByUsername(String username);

		  AppUser findByUsername(String username);

		  @Transactional
		  void deleteByUsername(String username);

		

}

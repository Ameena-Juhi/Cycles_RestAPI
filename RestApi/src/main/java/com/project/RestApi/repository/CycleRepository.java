package com.project.RestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.RestApi.entity.Cycle;

public interface CycleRepository extends JpaRepository<Cycle, Integer> {

}
